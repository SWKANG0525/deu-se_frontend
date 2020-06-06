/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author 강호동
 */
public class AnimationManage implements LoadInterface {

    @Override
    public void fadein(Node param, double duration) {
        FadeTransition fadeout = new FadeTransition(Duration.seconds(duration), param);
        fadeout.setFromValue(1.0);
        fadeout.setToValue(0.0);
        fadeout.play();
    }

    @Override
    public void fadeout(Node param, double duration) {
        FadeTransition fadein = new FadeTransition(Duration.seconds(duration), param);
        fadein.setFromValue(0.0);
        fadein.setToValue(1.0);
        fadein.play();
    }

}
