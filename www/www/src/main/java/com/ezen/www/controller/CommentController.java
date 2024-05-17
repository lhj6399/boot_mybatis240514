package com.ezen.www.controller;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
@Controller
public class CommentController {
    private final CommentService csv;

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody CommentVO cvo){
        log.info(">>>> cvo >> {}",cvo);
        int isOk = csv.post(cvo);
        return isOk > 0 ? "1" : "0";
    }

    /* select * from comment order by cno desc limit0,5 */
    @GetMapping("/list/{bno}/{page}")
    @ResponseBody
    public PagingHandler list(@PathVariable("bno")long bno, @PathVariable("page") int page){
        log.info(">>>> bno >>> page >> {} "+bno + "/" + page);
        PagingVO pgvo = new PagingVO(page, 5);

        PagingHandler ph = csv.getList(bno, pgvo);
        return ph;
    }

}
