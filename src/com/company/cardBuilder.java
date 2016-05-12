package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vassili.holenev on 12.05.2016.
 */
public class cardBuilder {

    public static void builder(int number) {
        if (number == 1) {
            String filepath = "\\Users\\vassili.holenev\\IdeaProjects\\Test\\src\\com\\company\\space.xml";
            File xmlFile = new File(filepath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();
                System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
                // получаем узлы с именем Language
                // теперь XML полностью загружен в память
                // в виде объекта Document
                NodeList nodeList = document.getElementsByTagName("Card");

                // создадим из него список объектов Language
                List<Card> cardList = new ArrayList<Card>();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    cardList.add(getLanguage(nodeList.item(i)));
                }

                // печатаем в консоль информацию по каждому объекту Language
                for (Card card : cardList) {
                    System.out.println(card.toString());
                }
            } catch (
                    Exception exc
                    )

            {
                exc.printStackTrace();
            }
        } else {
            String filepath = "\\Users\\vassili.holenev\\IdeaProjects\\Test\\src\\com\\company\\chaos.xml";
            File xmlFile = new File(filepath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();
                System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
                // получаем узлы с именем Language
                // теперь XML полностью загружен в память
                // в виде объекта Document
                NodeList nodeList = document.getElementsByTagName("Card");

                // создадим из него список объектов Language
                List<Card> cardList = new ArrayList<Card>();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    cardList.add(getLanguage(nodeList.item(i)));
                }

                // печатаем в консоль информацию по каждому объекту Language
                for (Card card : cardList) {
                    System.out.println(card.toString());
                }
            } catch (
                    Exception exc
                    )

            {
                exc.printStackTrace();
            }
        }
    }


    // создаем из узла документа объект Language
    private static Card getLanguage(Node node) {
        Card card = new Card();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            card.setId(Integer.parseInt(getTagValue("id", element)));
            card.setName(getTagValue("Name", element));
            card.setStrenght(Integer.parseInt(getTagValue("Strength", element)));
            card.setHealth(Integer.parseInt(getTagValue("Health", element)));
            card.setMana(Integer.parseInt(getTagValue("Mana", element)));
        }

        return card;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
