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

import javax.xml.namespace.QName;

/**
 * Created by rohan on 10/16/15.
 */
public class XmlParser {

    public static class XmlParserPodcasts extends DefaultHandler{
        static  ArrayList<PodCasts> podCastsList;
        static PodCasts podCast;
        StringBuilder sb ;
//        boolean mtitle=false;
//        boolean mdescription=false;
//        boolean mitem;
        public static ArrayList<PodCasts> getPodCastsList() {
            return podCastsList;
        }

        public static ArrayList<PodCasts> getList(InputStream in) throws IOException, SAXException {
            Log.d("DEmo", "Inside SAX");
            XmlParserPodcasts xmlParserPodcasts = new XmlParserPodcasts();
            Xml.parse(in, Xml.Encoding.UTF_8,xmlParserPodcasts);
            for (PodCasts p : xmlParserPodcasts.getPodCastsList()){
//                Log.d("Demo", p.toString());
            }



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
//            if (attributes.getLength() > 0) {
//                for (int i = 0; i < attributes.getLength(); i++) {
////                    Log.d  ("demp","name: " + attributes.getQName(i));
////                    Log.d("demo"," value: " + attributes.getValue(i));
//                }
//            }
            if(localName.equalsIgnoreCase("item")){
               podCast = new PodCasts();
            }else if(qName.equalsIgnoreCase("itunes:image")){
//                Log.d("Demo",attributes.getValue("href"));
            }else if(localName.equalsIgnoreCase("enclosure")){
//                Log.d("demo",attributes.getValue("url"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equalsIgnoreCase("item")) {
                podCastsList.add(podCast);
            }else if(localName.equalsIgnoreCase("title")) {
                Log.d("demo",sb.toString().trim());
            }else if(localName.equalsIgnoreCase("description")) {
//                Log.d("demo",sb.toString().trim());
            }else if(localName.equalsIgnoreCase("pubDate")) {
//                Log.d("demo", sb.toString().trim());
//            }else if(localName.equalsIgnoreCase("")) {
//
//            }else if(localName.equalsIgnoreCase("")) {
//
            }

            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch,start,length);
        }
    }

}
