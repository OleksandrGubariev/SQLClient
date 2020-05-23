package com.sqlclient.web.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class GetStaticResourcesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stylePath = request.getRequestURI().substring(1);

        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(stylePath);) {
            if (resourceAsStream == null) {
                log.error("File by path {} not found", stylePath);
                response.setStatus(404);
                response.getWriter().write(String.format("File by path %s not found", stylePath));
            } else {
                byte[] byteArray = resourceAsStream.readAllBytes();
                response.getOutputStream().write(byteArray);
            }
        } catch (IOException e) {
            log.error("Loading static resources error", e);
            throw new RuntimeException("Loading static resources error", e);
        }
    }
}
