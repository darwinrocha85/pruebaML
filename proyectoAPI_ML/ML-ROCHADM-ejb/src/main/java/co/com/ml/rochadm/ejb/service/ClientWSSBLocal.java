/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.rochadm.ejb.service;

import javax.ejb.Local;

/**
 *
 * @author rochadm
 */
@Local
public interface ClientWSSBLocal {

     
     public String getPrice(String item) throws Exception;
}
