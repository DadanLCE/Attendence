package com.attend.dream.controller;

import com.attend.dream.domain.Card;
import com.attend.dream.domain.RepairCard;
import com.attend.dream.service.CheckCardService;
import com.attend.dream.service.CheckReportService;
import com.attend.dream.service.PunchCardService;
import com.attend.dream.service.RepairCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CheckReportController {

    @Autowired
    CheckCardService checkCardService;
    @Autowired
    PunchCardService punchCardService;
    @Autowired
    RepairCardService repairCardService;
    @Autowired
    CheckReportService checkReportService;

    @GetMapping("/get/cardSumByGroup")
    @ResponseBody
    public List<Map> cardSum() {
        List<Map> cardSum = checkCardService.getEveryDayCard();
        //System.out.println(cardSum);
        return cardSum;
    }

    @GetMapping("/get/getAllCards")
    @ResponseBody
    public List<Card> allCards() {
        List<Card> allCards = punchCardService.getGetAllCards();
        System.out.println(allCards.size());
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        for (int i = 0; i<allCards.size();i++) {
            Card  card = allCards.get(i);
            Date morTime = card.getMorTime();
            String hour = sdf.format(morTime);
            System.out.println(hour);
            //System.out.println(card);
        }
        return allCards;
    }

    @GetMapping("/get/getRepairedCard")
    @ResponseBody
    public List<RepairCard> allRepairedCard() {

        List<RepairCard> repairCards = repairCardService.getCardsByCode("");
        return repairCards;
    }

    @GetMapping("/finalMvp")
    @ResponseBody
    public List<Card> adjust() throws ParseException {
        checkReportService.delete();
        List<Map> cardSum = checkCardService.getEveryDayCard();
        List<Card> allCards = punchCardService.getGetAllCards();
        List<RepairCard> repairCards = repairCardService.getCardsByCode("");
        //List<Card> adjust = new ArrayList<Card>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //打卡单状态标记
        for (int i = 0; i<allCards.size();i++) {
            Card  card = allCards.get(i);
            Date morTime = card.getMorTime();
            Date eveTime = card.getEveTime();
            //早上缺卡
            if (morTime == null) {
                card.setNote("旷工");
            }
            //晚上缺卡
            if (eveTime == null) {
//                String secondNote = card.getNote()+"旷工";
//                card.setNote(secondNote);
                card.setNote("旷工");
            }

            String finalNote = card.getNote();
            if ( finalNote == null || finalNote.length() <= 0 ){
                String morHour = sdf.format(morTime);
                String eveHour = sdf.format(eveTime);
                int x = morHour.compareTo("09");
               // System.out.println(x);
                if (morHour.compareTo("09") == 1) {
                    card.setNote("不正常");
                    //System.out.println(card.getNote());
                    //System.out.println(card.getNote());
                }
                if(eveHour.compareTo("21") != 1) {
                    //String n = card.getNote()+"早退";
                    card.setNote("不正常");
                }
            }
           // System.out.println("第一次");
           // System.out.println(card);
            checkReportService.insetAllDayTime(card);

            //checkReportService.updateCard(card);
            //System.out.println(card);
        }

        //补卡单状态补全
        for (int i = 0; i < repairCards.size(); i++) {

            System.out.println("补卡更新");
            RepairCard repairCard = repairCards.get(i);
            String name = repairCard.getCardCode();
            Date time = repairCard.getTime();

            //System.out.println(time);
            String day = sdf2.format(time);
            String dayTime = day + " 01:00:00";
            String dayTime2 = day + " 23:59:23";

            Date date1 = fds.parse(dayTime);
            Date date2 = fds.parse(dayTime2);

            List<Card> card = checkReportService.getSpecialRecord(name, date1, date2);
            //System.out.println(card);
            if (null == card || card.size() ==0) {
                String rn = repairCard.getName();
                String rc = repairCard.getCardCode();
                Date rt = repairCard.getTime();
                Card nullc = new Card();
                nullc.setCardCode(rc);
                nullc.setName(rn);
                String rs = sdf.format(rt);
                String s = sdf2.format(rt);
                nullc.setNote("正常");
//                if (rs.compareTo("12") != 1) {
//                    nullc.setMorTime(rt);
//                    checkReportService.insetMorTime(nullc);
//                }else {
//                    nullc.setEveTime(rt);
//                    checkReportService.insetEveTime(nullc);
//                }


                checkReportService.insetAllDayTime(nullc);

                //checkReportService.insetAllDayTime();
                //Card nullCard = checkReportService.getCardBycardCode(name);
                //checkReportService.insetAllDayTime();
            } else {

                Card x = card.get(0);
                String nt = x.getNote();

//                if (nt == null || nt.length()<=0) {
//                    x.setNote("正常");
//                    System.out.println(x);
//                    checkReportService.updateCard(x);
//                    continue;
//                }

                Date mTime = x.getMorTime();
                Date eTime = x.getEveTime();
                String t = sdf.format(time);
                System.out.println(t);
                int Intt = Integer.parseInt(t);
                int compare1 = 9;
                int compare2 = 22;
                System.out.println(mTime);
                System.out.println("111");
                System.out.println(x);
                if (mTime == null) {
                    //if (t.compareTo("09") != 1) {
                    x.setMorTime(time);
                    x.setNote("正常");
                    //System.out.println(x.getMorTime());
                    //System.out.println(x.getEveTime());
                    System.out.println(x);
                    checkReportService.updateMorCard(x);


                    // }
                } else {
                    String mt = sdf.format(mTime);
                    //System.out.println("比较结果"+t.compareTo("09"));
                    if (Intt < 9) {
                        x.setMorTime(time);
                        x.setNote("正常");
                        System.out.println("mor"+x);
                        checkReportService.updateMorCard(x);
                    }
                }

                if (eTime == null) {
                    //if (t.compareTo("22") == 1) {
                    x.setEveTime(time);
                    x.setNote("正常");
                    System.out.println("下午时间"+x.getEveTime());
                    System.out.println(x);
                    checkReportService.updateEveCard(x);

                    //}
                } else {
                    if (Intt > 22) {
                        String et = sdf.format(eTime);
                        x.setEveTime(time);
                        x.setNote("正常");
                        System.out.println();
                        System.out.println(x);
                        checkReportService.updateEveCard(x);

                    }
                }



                //checkReportService.updateCard(x);

            }
        }

        return allCards;
    }



}
