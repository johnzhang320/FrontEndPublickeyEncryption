package com.front.end.pk.encrypt.demo.controller;

import com.front.end.pk.encrypt.demo.dto.AgentTableDemoDto;
import com.front.end.pk.encrypt.demo.dto.AgentTableDto;
import com.front.end.pk.encrypt.demo.exception.MyPasswordException;
import com.front.end.pk.encrypt.demo.fepke_api.EncoderService;
import com.front.end.pk.encrypt.demo.model.AgentTable;
import com.front.end.pk.encrypt.demo.repository.AgentTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BcryptEncoderAPIController {
    private final EncoderService encoderService;
    private final AgentTableRepository agentTableRepository;

  //  private final AgentTableMapper agentTableMapper;

    private final ModelMapper modelMapper;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<Map<String,Object>> findByUsername(@PathVariable String username) {

        return agentTableRepository.findAgentTableByUserName(username)
                .map(x->{
                    AgentTableDto agentTableDto= modelMapper.map(x,AgentTableDto.class);
                    agentTableDto.setMessage("Found User: "+agentTableDto.getUserName()+"  record!");
                    Map<String,Object> map= new LinkedHashMap<>();
                    map.put("Found Timestamp", LocalDateTime.now());
                    map.put("status",HttpStatus.FOUND.value());
                    map.put("AgentTableDto",agentTableDto);
                    return new ResponseEntity<>(map,HttpStatus.FOUND);

                })
                .orElseThrow(()->
                   new MyPasswordException("Username "+username+" Not Found " )
                );
    }

    @GetMapping("/findAllUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<AgentTableDto> findAllUsers() {
       List<AgentTable> agentTableList= agentTableRepository.findAll();
       List<AgentTableDto>  agentTableDtoList  =  agentTableList.stream()
               .map(x->{
                  AgentTableDto agentTableDto = modelMapper.map(x, AgentTableDto.class);
                  return agentTableDto;
               }).collect(Collectors.toList());
       return agentTableDtoList;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/checkPassword/{username}/{passwordPlainText}")
    public AgentTableDto checkPassword(@PathVariable String username,@PathVariable String passwordPlainText)
            {
            String bcryptedpwd=null;
            log.info("bcryptedpwd="+bcryptedpwd);

            AgentTable agentTable = agentTableRepository.findAgentTableByUserName(username)
                    .orElseThrow(()->
                        new MyPasswordException("Username: "+username+" Not Found")
                    );

            bcryptedpwd = agentTable.getPassword();
            boolean checkResult = encoderService.checkPasswordExist(passwordPlainText,bcryptedpwd);

            AgentTableDto agentTableDto= modelMapper.map(agentTable,AgentTableDto.class);

            agentTableDto.setPasswordMatched(checkResult);
            if (checkResult) {
                agentTableDto.setMessage("Entered Password:"+passwordPlainText+" is used for user:"+username);
            } else {
                agentTableDto.setMessage("Entered Password:"+passwordPlainText+" is new for user:"+username);
            }
            return agentTableDto;
    }

    /**
     * Postman Test
     * @param passwordPlainText
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/savePassword/{username}/{passwordPlainText}")
    public AgentTableDto savePassword(@PathVariable String username,@PathVariable String passwordPlainText)
    {

        String bcryptedpwd=encoderService.bcryptEncodingPassword(passwordPlainText);
        log.info("bcryptedpwd="+bcryptedpwd);

        AgentTable agentTable = agentTableRepository.findAgentTableByUserName(username)
                .orElseThrow(()->
                        new MyPasswordException("Username: "+" Not Found " )
                );
        agentTable.setPassword(bcryptedpwd.trim());
        agentTableRepository.save(agentTable);
        AgentTableDto agentTableDto= modelMapper.map(agentTable,AgentTableDto.class);
        return agentTableDto;

    }

}
