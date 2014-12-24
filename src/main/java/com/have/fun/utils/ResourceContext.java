package com.have.fun.utils;

import com.google.common.collect.Lists;
import com.have.fun.entity.VoteItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/23 Time: 23:28
 * </pre>
 */
public class ResourceContext {

    private static final Logger                           LOG               = LoggerFactory.getLogger(ResourceContext.class);
    private static TreeSet<VoteItem>                      voteItemSortedSet = new TreeSet<VoteItem>();
    private static ConcurrentHashMap<Integer, AtomicLong> voteResultMap     = new ConcurrentHashMap<Integer, AtomicLong>();
    private static ResourceContext                        instance          = null;

    public static synchronized ResourceContext getInstance() {
        if (instance == null) {
            instance = new ResourceContext();
            instance.init();
        }
        return instance;
    }

    private void init() {
        // init vote items
        try {
            InputStream inputStream = ResourceContext.class.getClassLoader().getSystemResourceAsStream(FunnyConstants.ITEM_FILE);
            if (inputStream == null) {
                // String fileName = new
                // URI(ResourceContext.class.getResource(FunnyConstants.ITEM_FILE).toString()).toString();
                String fileName = new URI(
                                          ResourceContext.class.getClassLoader().getResource(FunnyConstants.ITEM_FILE).toString()).toString();

                File file = new File(fileName.replace("file:", ""));
                inputStream = new FileInputStream(file);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            while (line != null) {
                List<String> array = Lists.newArrayList(FunnyConstants.SPLITTER.split(line));
                if (array != null && array.size() == 2) {
                    voteItemSortedSet.add(new VoteItem(Integer.parseInt(array.get(0)), array.get(1)));
                }
                line = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            LOG.error("load item file fail. ", e);
        } catch (IOException e) {
            LOG.error("load item file fail. ", e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // init vote result
        for (VoteItem voteItem : voteItemSortedSet) {
            voteResultMap.put(voteItem.getId(), new AtomicLong(0));
        }
    }

    private ResourceContext() {

    }

    public void increase(Integer id) {
        voteResultMap.get(id).getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        final ResourceContext resourceContext = ResourceContext.getInstance();
        for (VoteItem voteItem : voteItemSortedSet) {
            System.out.println(voteItem);
        }
        System.out.println(voteResultMap);

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        resourceContext.increase(new Random().nextInt(3) + 1);
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("----------");
        System.out.println(voteResultMap);

    }

    public List<VoteItem> getVoteItems() {
        return Lists.newArrayList(voteItemSortedSet.iterator());
    }
}
