package com.github.yeecode.easyrpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        System.out.println("*************************************************");
        System.out.println(" ______                _____  _____   _____ \n" +
                "|  ____|              |  __ \\|  __ \\ / ____|\n" +
                "| |__   __ _ ___ _   _| |__) | |__) | |     \n" +
                "|  __| / _` / __| | | |  _  /|  ___/| |     \n" +
                "| |___| (_| \\__ \\ |_| | | \\ \\| |    | |____ \n" +
                "|______\\__,_|___/\\__, |_|  \\_\\_|     \\_____|\n" +
                "                  __/ |                     \n" +
                "                 |___/                      \n");
        System.out.println("*************************************************");
        System.out.println("EasyRpcServer start successfully!");
        System.out.println("Ready to receive EasyRpcClient call.");
    }


}