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
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/23 Time: 23:28
 * </pre>
 */
public class ResourceContext {

    private static final Logger                              LOG               = LoggerFactory.getLogger(ResourceContext.class);
    private static TreeSet<VoteItem>                         voteItemSortedSet = new TreeSet<VoteItem>();
    private static ConcurrentHashMap<Integer, AtomicInteger> voteResultMap     = new ConcurrentHashMap<Integer, AtomicInteger>();
    private static ResourceContext                           instance          = null;
    private String                                           itemPath          = null;

    public static synchronized ResourceContext getInstance(String itemPath) {
        if (instance == null) {
            instance = new ResourceContext();
            instance.setItemPath(itemPath);
            instance.init();
        }
        return instance;
    }

    public void setItemPath(String itemPath) {
        this.itemPath = itemPath;
    }

    private void init() {
        // init vote items
        try {
            File file = new File(itemPath);
            InputStream inputStream = new FileInputStream(file);
            /*
             * if (inputStream == null) { String fileName = new URI(
             * ResourceContext.class.getClassLoader().getResource(itemPath).toString()).toString(); File file = new
             * File(fileName.replace("file:", "")); inputStream = new FileInputStream(file); LOG.info("load file path="
             * + file.getAbsolutePath()); }
             */
            LOG.info("load file path=" + file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            while (line != null) {
                List<String> array = Lists.newArrayList(FunnyConstants.SPLITTER.split(line));
                if (array != null && array.size() == 5) {
                    VoteItem voteItem = new VoteItem(Integer.parseInt(array.get(0)), array.get(1), array.get(2),
                                                     array.get(3), Integer.parseInt(array.get(4)));
                    voteItemSortedSet.add(voteItem);
                }
                line = br.readLine();
            }

            br.close();
            // Executors executorService = Executors.newFixedThreadPool(1);
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    LOG.info("start to save records.");
                    instance.saveRecords();
                    LOG.info("end to save records.");
                }
            }, 1, 1, TimeUnit.MINUTES);

        } catch (FileNotFoundException e) {
            LOG.error("load item file fail. ", e);
        } catch (IOException e) {
            LOG.error("load item file fail. ", e);
        }
        // init vote result
        for (VoteItem voteItem : voteItemSortedSet) {
            voteResultMap.put(voteItem.getId(), new AtomicInteger(voteItem.getRecords()));
        }
    }

    private ResourceContext() {

    }

    public void increase(Integer id) {
        voteResultMap.get(id).getAndIncrement();
    }

    public void saveRecords() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(itemPath)));
            for (VoteItem voteItem : voteItemSortedSet) {
                voteItem.setRecords(voteResultMap.get(voteItem.getId()).intValue());
                String line = voteItem.toString();
                bw.write(line);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            LOG.error("load item file fail. ", e);
        } catch (IOException e) {
            LOG.error("load item file fail. ", e);
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                LOG.error("close file error.", e);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        final ResourceContext resourceContext = ResourceContext.getInstance("/Users/apple/privacy/works/idea/funny/src/main/resources/item.txt");
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
        resourceContext.saveRecords();

    }

    public List<VoteItem> getVoteItems() {
        return Lists.newArrayList(voteItemSortedSet.iterator());
    }

    public Integer getRecordsById(Integer id) {
        return voteResultMap.get(id).get();
    }
}
