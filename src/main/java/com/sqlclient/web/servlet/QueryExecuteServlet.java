package com.sqlclient.web.servlet;

import com.sqlclient.entity.QueryResult;
import com.sqlclient.service.QueryExecuteService;
import com.sqlclient.web.templater.TemplateEngineFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class QueryExecuteServlet extends HttpServlet {

    private final QueryExecuteService queryExecuteService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Request get SQL client page");
        response.setContentType("text/html;charset=utf-8");
        TemplateEngineFactory.process("index", response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String query = request.getParameter("sqlRequest");
        if (query == null) {
            response.sendRedirect("/");
            return;
        }
        log.info("Executing query {}", query);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("query", query);
        QueryResult queryResult = queryExecuteService.queryExecute(query);
        paramsMap.put("queryResult", queryResult);

        response.setContentType("text/html;charset=utf-8");
        TemplateEngineFactory.process("index", paramsMap, response.getWriter());
    }
}
