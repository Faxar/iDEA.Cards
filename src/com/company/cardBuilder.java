package com.company;

//Guide taken http://prologistic.com.ua/kak-chitat-xml-fajl-v-java-ispol-zuem-dom-parser.html

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

    public static void builder(Deck deck, int number) {
            String filepath = feedPath(number);
            File xmlFile = new File(filepath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();
                // получаем узлы с именем Card
                // теперь XML полностью загружен в память
                // в виде объекта Document
                NodeList nodeList = document.getElementsByTagName("Card");

                // создадим из него список объектов Card
                List<Card> cardList = new ArrayList<Card>();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    cardList.add(getLanguage(nodeList.item(i)));
                }

                // печатаем в консоль информацию по каждому объекту Card
                for (Card card : cardList) {
                    deck.populateDeck(card);
                    //System.out.println(card.toString());
                }
            } catch (
                    Exception exc
                    )

            {
                exc.printStackTrace();
            }
    }


    // создаем из узла документа объект Card
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

    private static String feedPath(int number){
        if(number == 1){
            return "\\Users\\vassili.holenev\\IdeaProjects\\Test\\src\\decks\\space.xml";
        } else if (number == 2) {
            return "\\Users\\vassili.holenev\\IdeaProjects\\Test\\src\\decks\\orks.xml";
        } else if (number == 3){
            return "\\Users\\vassili.holenev\\IdeaProjects\\Test\\src\\decks\\chaos.xml";
        } else if (number == 4){
            return "\\Users\\vassili.holenev\\IdeaProjects\\Test\\src\\decks\\necrons.xml";
        } return null;
    }

}
