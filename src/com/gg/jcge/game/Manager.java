/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gg.jcge.game;


/**
 *
 * @author scalpa
 */
public interface Manager<T extends Displayable, U> extends Displayable<T> {

    void add(U... u);

}
