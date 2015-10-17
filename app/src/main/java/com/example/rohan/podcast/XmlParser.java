package com.example.rohan.podcast;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by rohan on 10/16/15.
 */
public class XmlParser {

    public static class XmlParserPodcasts extends DefaultHandler {
        static ArrayList<PodCasts> podCastsList;
        static PodCasts podCast;
        StringBuilder sb;

        public static ArrayList<PodCasts> getPodCastsList() {
            return podCastsList;
        }

        public static ArrayList<PodCasts> getList(InputStream in) throws IOException, SAXException {
            Log.d("DEmo", "Inside SAX");
            XmlParserPodcasts xmlParserPodcasts = new XmlParserPodcasts();
            Xml.parse(in, Xml.Encoding.UTF_8, xmlParserPodcasts);
//            for (PodCasts p : xmlParserPodcasts.getPodCastsList()) {
//                Log.d("Demo", p.toString());
//            }
            return podCastsList;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            podCastsList = new ArrayList<>();
            sb = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("item") || localName.equals("channel")) {
                podCast = new PodCasts();
            } else if (qName.equals("itunes:image")) {
                podCast.setImageURL(attributes.getValue("href"));
            } else if (localName.equals("enclosure")) {
                podCast.setUrl(attributes.getValue("url"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("item")) {
                podCastsList.add(podCast);
            } else if (localName.equals("title")) {
                podCast.setTitle(sb.toString().trim());
            } else if (localName.equals("description")) {
                podCast.setDescription(sb.toString().trim());
            } else if (localName.equals("pubDate")) {
                podCast.setPublishDate(sb.toString().trim());
            } else if (localName.equals("duration")) {
                podCast.setDuration(sb.toString().trim());
            }

            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }
    }

}
