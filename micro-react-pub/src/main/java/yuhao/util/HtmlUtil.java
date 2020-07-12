package yuhao.util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * @description:
 * @author: HaKu
 * @create: 2020-05-27 13:09
 **/
public class HtmlUtil extends HTMLEditorKit.ParserCallback {

    private static HtmlUtil html2Text = new HtmlUtil();

    StringBuffer s;

    public HtmlUtil() {
    }

    public void parse(String str) throws IOException {

        InputStream iin = new ByteArrayInputStream(str.getBytes());
        Reader in = new InputStreamReader(iin);
        s = new StringBuffer();
        ParserDelegator delegator = new ParserDelegator();
        // the third parameter is TRUE to ignore charset directive
        delegator.parse(in, this, Boolean.TRUE);
        iin.close();
        in.close();
    }

    @Override
    public void handleText(char[] text, int pos) {
        s.append(text);
    }

    public String getText() {
        return s.toString();
    }

    public static String getContent(String str) {
        try {
            html2Text.parse(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return html2Text.getText();
    }

//    public static void main(String[] args) {
//        String txt = HtmlUtil.getContent("<p><strong>肖胜方代表认为，随着我国社会发展现代化、信息化程度越来越高，义务教育的广泛普及，现代未成年人的心智已比上世纪七十年代的未成年人更加成熟。鉴于目前犯罪日渐低龄化，下调未成年人刑事责任年龄的起点符合社会发展规律。</strong></p><p><strong>“如今，未成年人的心理发育普遍出现早熟现象。十三周岁的少年基本已经就读初中，具备相当的辨识力和自控力，已能够理解其实施行为的性质和后果。”肖胜方解释说，“一方面，受电视、互联网、新媒体的影响，当下的未成年人更容易获得丰富的知识，了解新鲜事物。但同时也会接收到负面信息，影响其心理发育。其后果是，未成年人犯罪低龄化趋势凸显，主观恶性变大，在一定程度上危害社会稳定和公民生命财产安全。<span class=\"ql-cursor\">\uFEFF</span></strong></p>");
//        System.out.println(txt);
//
//    }
}
