package com.company;

//Guide taken http://prologistic.com.ua/kak-chitat-xml-fajl-v-java-ispol-zuem-dom-parser.html

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InterruptedIOException;
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
                    cardList.add(getCard(nodeList.item(i)));
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
//    private static Card getLanguage(Node node) {
//        if (node.getNodeType() == Node.ELEMENT_NODE) {
//            Element element = (Element) node;
//            Card card = new Card();
//            card.setId(Integer.parseInt(getTagValue("id", element)));
//            card.setName(getTagValue("Name", element));
//            card.setMana(Integer.parseInt(getTagValue("Mana", element)));
//            card.setIsMinion(Integer.parseInt(getTagValue("Minion", element)));
//            if(Integer.parseInt(getTagValue("Minion", element)) == 1) {
//                card.setStrenght(Integer.parseInt(getTagValue("Strength", element)));
//                card.setHealth(Integer.parseInt(getTagValue("Health", element)));
//                return card;
//            }else if (Integer.parseInt(getTagValue("Minion", element)) == 0){
//                if (Integer.parseInt(getTagValue("spellType", element))== 1){
//                    card.setSpellType(Integer.parseInt(getTagValue("spellType", element)));
//                    card.setPower(Integer.parseInt(getTagValue("Power", element)));
//                    card.setDesctiption(getTagValue("Description", element));
//                    return card;
//                } else if (Integer.parseInt(getTagValue("spellType", element)) == 2){
//                    card.setSpellType(Integer.parseInt(getTagValue("spellType", element)));
//                    card.setModificator(Integer.parseInt(getTagValue("modificator", element)));
//                    card.setDesctiption(getTagValue("Description", element));
//                    return card;
//                } else if (Integer.parseInt(getTagValue("spellType", element)) == 3){
//                    card.setSpellType(Integer.parseInt(getTagValue("spellType", element)));
//                    card.setHeal(Integer.parseInt(getTagValue("heal", element)));
//                    card.setDesctiption(getTagValue("Description", element));
//                    return card;
//                }
//            }
//
//        }
//        System.out.println("return null");
//        return null;
//
//    }

    private static Card getCard(Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            if(Integer.parseInt(getTagValue("Minion", element)) == 1){
                Minion minion = new Minion();
                minion.setIsMinion(Integer.parseInt(getTagValue("Minion", element)));
                minion.setId(Integer.parseInt(getTagValue("id", element)));
                minion.setName(getTagValue("Name", element));
                minion.setStrenth(Integer.parseInt(getTagValue("Strength", element)));
                minion.setHealth(Integer.parseInt(getTagValue("Health", element)));
                minion.setMana(Integer.parseInt(getTagValue("Mana", element)));
                return minion;
            } else if (Integer.parseInt(getTagValue("Minion", element)) == 0){
                if(Integer.parseInt(getTagValue("spellType", element)) == 1){
                    DamageSpell damageSpell = new DamageSpell();
                    damageSpell.setIsMinion(Integer.parseInt(getTagValue("Minion", element)));
                    damageSpell.setSpellType(Integer.parseInt(getTagValue("spellType", element)));
                    damageSpell.setId(Integer.parseInt(getTagValue("id", element)));
                    damageSpell.setName(getTagValue("Name", element));
                    damageSpell.setPower(Integer.parseInt(getTagValue("Power", element)));
                    damageSpell.setMana(Integer.parseInt(getTagValue("Mana", element)));
                    damageSpell.setDesctiption(getTagValue("Description", element));
                    return damageSpell;
                } else if(Integer.parseInt(getTagValue("spellType", element)) == 2){
                    BuffSpell buffSpell = new BuffSpell();
                    buffSpell.setIsMinion(Integer.parseInt(getTagValue("Minion" , element)));
                    buffSpell.setSpellType(Integer.parseInt(getTagValue("spellType", element)));
                    buffSpell.setId(Integer.parseInt(getTagValue("id", element)));
                    buffSpell.setName(getTagValue("Name", element));
                    buffSpell.setModificator(Integer.parseInt(getTagValue("modificator", element)));
                    buffSpell.setMana(Integer.parseInt(getTagValue("Mana", element)));
                    buffSpell.setDesctiption(getTagValue("Description", element));
                    return buffSpell;
                } else if (Integer.parseInt(getTagValue("spellType", element)) == 3){
                    HealSpell healSpell = new HealSpell();
                    healSpell.setIsMinion(Integer.parseInt(getTagValue("Minion", element)));
                    healSpell.setSpellType(Integer.parseInt(getTagValue("spellType", element)));
                    healSpell.setId(Integer.parseInt(getTagValue("id", element)));
                    healSpell.setName(getTagValue("Name", element));
                    healSpell.setHeal(Integer.getInteger(getTagValue("heal", element)));
                    healSpell.setMana(Integer.getInteger(getTagValue("Mana", element)));
                    healSpell.setDesctiption(getTagValue("Description", element));
                    return healSpell;
                }
            }
        }
        System.out.println("return null");
        return null;
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
