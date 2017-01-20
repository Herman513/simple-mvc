package org.simplemvc.web.servlet;
import org.slf4j.Logger;

import java.util.HashMap;

/**
 * Created by wuyuhuan on 2017/1/20.
 * Usage：存放所有路由,使用HashMap
 */
public class Routers {
    private static final Logger logger= org.slf4j.LoggerFactory.getLogger(Routers.class);
    private HashMap<String,Route> routes=new HashMap<>();

    public Routers() {
    }
    public void addRoute(Route route) throws Exception {
        if(routes.containsKey(route.getPath())) {
            logger.error("==========duplicate path===========");
            logger.error("Alreadly exist route:"+routes.get(route.getPath()));
            logger.error("Duplicate route:"+route);
            throw new Exception("It is a duplicated path, check you path :"+route.getPath());
        }
        this.routes.put(route.getPath(),route);
        logger.info("==========add route: "+route.toString()+"===========");
    }
    public Route getRoute(String path){
        if(routes.containsKey(path))
            return routes.get(path);
        return null;
    }
    public HashMap<String,Route> getRoutes(){
        return routes;
    }
    public void removeRoute(String path){
        routes.remove(path);
    }
}
