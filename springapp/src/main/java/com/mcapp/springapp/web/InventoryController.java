package com.mcapp.springapp.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.service.CrosswordService;
import com.mcapp.springapp.service.interfaces.IPalabraService;

@Controller
public class InventoryController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Resource
    private IPalabraService srvPalabra;
    
    @Resource
    private CrosswordService srvCrossword;

    @RequestMapping(value="/hello.html")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("palabras", this.srvPalabra.getPalabrasDto());
        myModel.put("crucigrama", new JSONObject(this.srvCrossword.generateCrossword(4, 20)));

        return new ModelAndView("hello", "model", myModel);
    }
}