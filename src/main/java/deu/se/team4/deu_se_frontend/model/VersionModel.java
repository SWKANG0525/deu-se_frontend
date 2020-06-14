/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import deu.se.team4.deu_se_frontend.vo.VersionVO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 강호동
 */
final class VersionModel extends BaseModel {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    private final String FXML1 = "/fxml/FXMLGenerateAccountView.fxml";
    private final String FXML2 = "/fxml/FXMLLoginView.fxml";
    private static final String MODULE_URL = "version";
    private VersionVO version_vo;
    
   public VersionModel() {

        createModel(null);

    }

    @Override
    void createModel(String stringJson) {
        version_vo = gson.fromJson(super.getSynchronous(MODULE_URL), VersionVO.class);
    }

    @Override
    public void printModel() {
        System.out.println("version : " + version_vo.getVersion());
    }


    String VersionCheck() {
        String server_version = version_vo.getVersion();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("version.dat"));
            VersionVO client_vo = (VersionVO) ois.readObject();
            if (server_version.equals(client_vo.getVersion())) {
                System.out.println("클라이언트가 최신 버전입니다");
                ois.close();
                return FXML2;
            } else {
                System.out.println("업데이트가 존재합니다. 클라이언트를 업데이트 해주세요");
                oos = new ObjectOutputStream(new FileOutputStream("version.dat"));
                oos.writeObject(version_vo);
                oos.close();
                return null;

            }

        } catch (FileNotFoundException e) {
            try {
                oos = new ObjectOutputStream(new FileOutputStream("version.dat"));
                oos.writeObject(version_vo);
                oos.close();
                return FXML1;
            } catch (IOException ea) {
                ea.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(APICenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "none";

    }
}
