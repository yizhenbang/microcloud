package com.yzb.gateway.action;

import com.yzb.gateway.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhenBang-Yi
 * @ClassName DynicRouteAction
 * @date 2022/7/24 12:47
 */
@RequestMapping("routes/*")
@RestController
public class DynicRouteAction {
    @Autowired
    private RouteService routeService;

    @PostMapping("/add")
    public void add(@RequestBody RouteDefinition definition) {
        this.routeService.insertRoute(definition);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        this.routeService.deleteRoute(id);
    }

    @PostMapping("/update")
    public Object update(@RequestBody RouteDefinition definition) {
        return this.routeService.updateRoute(definition);
    }

    @GetMapping("/getRoutes")
    public Object getRoutes() {
        return this.routeService.getRoutes();
    }
}
