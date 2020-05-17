package com.sqlclient.web.servlet;

import com.sqlclient.web.templater.TemplateEngineFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@WebServlet(name = "QueryExecuteServlet", urlPatterns = "/")
public class QueryExecuteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        log.info("Request get SQL client page");

        response.setContentType("text/html;charset=utf-8");
        TemplateEngineFactory.process(request, response, "hello", null);
    }
}
