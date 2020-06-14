/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import deu.se.team4.deu_se_frontend.ParkArea;
import deu.se.team4.deu_se_frontend.ParkComponent;
import deu.se.team4.deu_se_frontend.vo.ParkVO;

/**
 *
 * @author 강호동
 */
public class ParkModel {

    public ParkComponent makeParkList(int limit) {

        ParkComponent allArea = new ParkArea("주차장 전체구역");
        ParkComponent aArea = new ParkArea("A");
        ParkComponent bArea = new ParkArea("B");
        ParkComponent cArea = new ParkArea("C");
        allArea.add(aArea);
        allArea.add(bArea);
        allArea.add(cArea);

        aArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                1, "11가 1111", 30, "2020-06-24","A"
        ));

        aArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                2, "22나 2222", 30, "2020-06-23","A"
        ));
        aArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                3, "33다 3333", 30, "2020-06-25","A"
        ));
        bArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                1, "44가 4444", 30, "2020-06-26","B"
        ));
        bArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                2, "55나 5555", 30, "2020-06-28","B"
        ));
        bArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                3, "66다 6666", 30, "2020-06-25","B"
        ));
        cArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                1, "77가 7777", 30, "2020-06-26","C"
        ));
        cArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                2, "88나 8888", 30, "2020-06-30","C"
        ));
        cArea.add(new ParkVO( // int park_pos,string car_number, int book_num, string exp
                3, "99다 9999", 30, "2020-07-01","C"
        ));
        return allArea;
    }

}
