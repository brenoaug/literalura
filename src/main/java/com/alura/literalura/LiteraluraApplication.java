package com.alura.literalura;

import com.alura.literalura.menu.MenuAutores;
import com.alura.literalura.menu.MenuLivros;
import com.alura.literalura.menu.MenuPrincipal;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    final MenuLivros menuLivros;
    final MenuAutores menuAutores;

    public LiteraluraApplication(MenuLivros menuLivros, MenuAutores menuAutores) {
        this.menuLivros = menuLivros;
        this.menuAutores = menuAutores;
    }

    public static void main(String[] args) {

        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal(menuLivros, menuAutores);
        menuPrincipal.iniciar();
    }
}
