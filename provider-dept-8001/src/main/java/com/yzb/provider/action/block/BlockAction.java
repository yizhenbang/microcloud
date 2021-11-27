package com.yzb.provider.action.block;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/errors/*")
public class BlockAction {
    @GetMapping("/block")
    public Object blockMethod() {
        HashMap block = new HashMap();
        block.put("status", HttpServletResponse.SC_BAD_REQUEST);
        block.put("message", "Blocked by Sentinel (flow limiting)");
        return block;
    }
}
