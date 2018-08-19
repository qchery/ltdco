package com.qchery.ltdco.processor;

import com.qchery.ltdco.LtdCoConstants;
import com.qchery.ltdco.entity.LtdCo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chery
 * @date 2018/8/19 13:27
 */
public class LtdcoPageProcessor implements PageProcessor {

    private static final Pattern PATTERN =Pattern.compile("(.*)（(\\d+)）");

    @Override
    public void process(Page page) {
        if (StringUtils.endsWith(page.getUrl().toString(), "index.html")) {
            Document document = Jsoup.parse(page.getRawText());

            Element h1 = document.getElementsByTag("h1").get(0);
            Element table = h1.parent().getElementsByTag("table").get(0);

            Elements elements = table.getElementsByTag("a");
            for (Element href : elements) {
                page.addTargetRequest(LtdCoConstants.CONTEXT_PATH + href.attr("href"));
            }
        } else {
            List<String> liContents = page.getHtml().xpath("//ol/li/text()").all();

            List<LtdCo> ltdCos = new ArrayList<>();
            for (String liContent : liContents) {
                Matcher matcher = PATTERN.matcher(liContent);
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    String code = matcher.group(2);
                    ltdCos.add(new LtdCo(name, code));
                }
            }
            page.getResultItems().put(LtdCoConstants.LTD_COS, ltdCos);
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setTimeOut(5000).setCycleRetryTimes(3).setSleepTime(100);
    }
}
