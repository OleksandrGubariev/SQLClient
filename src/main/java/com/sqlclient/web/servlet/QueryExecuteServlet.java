package com.sqlclient.web.servlet;

import com.sqlclient.ServiceLocator;
import com.sqlclient.entity.QueryResult;
import com.sqlclient.entity.QueryType;
import com.sqlclient.service.QueryExecuteService;
import com.sqlclient.util.QueryTypeParser;
import com.sqlclient.web.templater.TemplateEngineFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(name = "QueryExecuteServlet", urlPatterns = "/")
public class QueryExecuteServlet extends HttpServlet {
    private final QueryExecuteService queryExecuteService = ServiceLocator.getService(QueryExecuteService.class);
    private final QueryTypeParser queryTypeParser = ServiceLocator.getService(QueryTypeParser.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        log.info("Request get SQL client page");


        response.setContentType("text/html;charset=utf-8");
        TemplateEngineFactory.process(request, response, "index", null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = request.getParameter("sqlRequest");
        if (query == null) {
            response.sendRedirect("/");
            return;
        }
        log.info("Executing query {}", query);
        QueryType queryType = queryTypeParser.queryParse(query);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("query", query);
        if (queryType == null) {
            paramsMap.put("systemInfo", "ERROR: syntax error");
        } else if (queryType == QueryType.SELECT) {
            QueryResult queryResult = queryExecuteService.queryExecuteSelect(query);
            paramsMap.put("columnNames", queryResult.getColumnName());
            paramsMap.put("columnValueLists", queryResult.getColumnValues());
        } else {
            int count = queryExecuteService.queryExecute(query);
            paramsMap.put("systemInfo", count);
        }

        response.setContentType("text/html;charset=utf-8");
        TemplateEngineFactory.process(request, response, "index", paramsMap);
    }
}
