package org.mintleaf.modules.parse.service;

import org.mintleaf.modules.parse.entity.Dt;
import org.mintleaf.modules.parse.entity.VideUrlOne;
import org.mintleaf.modules.parse.entity.Video;
import org.mintleaf.modules.parse.entity.VideoUrl;
import org.mintleaf.modules.parse.utils.FileUtil;
import org.mintleaf.modules.parse.utils.HtmlUtil;
import org.mintleaf.modules.parse.utils.PPTVUtil;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

@Service
public class PPTVProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(5000);

    public VideoUrl runSpider(String url) {
        final VideoUrl[] videoUrls = {new VideoUrl()};
        Spider.create(this)
                .thread(1)
                .addUrl(url)
                .addPipeline((resultItems, task) -> videoUrls[0] = resultItems.get("videoUrl"))
                .run();

        return videoUrls[0];
    }

    @Override
    public void process(Page page) {
        String content = page.getRawText();
        // 获取 cid
        String cid = HtmlUtil.p1(content, "\"cid\":(.*?),");
        // 获取视频分段信息url
        String url = PPTVUtil.getUrl(cid);
//        printUrl(PPTVUtil.getVideo(url));
        VideoUrl videoUrl = getUrlJson(PPTVUtil.getVideo(url));
        if (videoUrl.getTitle() == null) {
            //skip this page
            page.setSkip(true);
        } else {
            page.putField("videoUrl", videoUrl);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    private static void printUrl(Video video) {
        List<Dt> dts = video.getDts();
        System.out.println("标题:" + video.getName());
        System.out.println("缩略图: " + "http://v.img.pplive.cn/cs128x72/" + video.getPic());
        System.out.println();

        for (int i = 0; i < dts.size(); i++) {
            Dt dt = dts.get(i);
            System.out.println((i == 0 ? "[标清" : i == 1 ? "[高清" : "[超清") + "-" + FileUtil.getPrintSize(dt.getFs()) + "]");
            // 计算 k 值
            String k = PPTVUtil.calcK(dt.getKey(), dt.getFlag(), dt.getSh(), dt.getSt(), dt.getId(), dt.getBh(), dt.getIv());
            // 计算 key 值
            String key = PPTVUtil.calcKey(dt.getSt());

            for (int ii = 0; ii < dt.getSgms(); ii++) {
                String url = "http://" + dt.getSh() + "/" + ii + "/" + dt.getRid();
                String params = "?fpp.ver=1.3.0.24&key=" + key + "&k=" + k + "&type=web.fpp";
                System.out.println(url + params);
            }
            System.out.println();
        }
    }

    private static VideoUrl getUrlJson(Video video) {
        VideoUrl videoUrl = new VideoUrl();
        List<Dt> dts = video.getDts();
        videoUrl.setTitle(video.getName());
        videoUrl.setThumbnail("http://v.img.pplive.cn/cs128x72/" + video.getPic());

        List<VideUrlOne> videUrlOnes = new ArrayList<>();
        for (int i = 0; i < dts.size(); i++) {
            VideUrlOne videUrlOne = new VideUrlOne();
            Dt dt = dts.get(i);
            videUrlOne.setDefinition((i == 0 ? "[标清" : i == 1 ? "[高清" : "[超清") + "-" + FileUtil.getPrintSize(dt.getFs()) + "]");
            // 计算 k 值
            String k = PPTVUtil.calcK(dt.getKey(), dt.getFlag(), dt.getSh(), dt.getSt(), dt.getId(), dt.getBh(), dt.getIv());
            // 计算 key 值
            String key = PPTVUtil.calcKey(dt.getSt());

            List<String> urlSeg = new ArrayList<>();
            for (int ii = 0; ii < dt.getSgms(); ii++) {
                String url = "http://" + dt.getBh() + "/" + ii + "/" + dt.getRid();
                String params = "?fpp.ver=1.3.0.24&key=" + key + "&k=" + k + "&type=web.fpp";
                urlSeg.add(url + params);
            }
            videUrlOne.setUrlSegms(urlSeg);
//            videUrlOne.setDu(dt.getDu());
//            videUrlOne.setSegs(dt.getSgms());
            videUrlOnes.add(videUrlOne);
        }
        videoUrl.setUrls(videUrlOnes);
        return videoUrl;
    }

}