/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.vo;

import java.io.Serializable;

/**
 *
 * @author 강호동
 */
public class VersionVO implements Serializable {

    private static final long serialVersionUID = -2345394654625325945L;
    private String version;

    public VersionVO(String version) {
        this.version = version;

    }

    public String getVersion() {
        return this.version;
    }

}
