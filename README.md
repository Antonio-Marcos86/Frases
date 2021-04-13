## APP Frase Nerd
   O aplicativo gera frases aleatórias do mundo Nerd, ao clicar no botão 
![Capa_Frases_Nerd](https://user-images.githubusercontent.com/71250901/109045891-e5683480-76b2-11eb-9584-9dece9f0c0ed.jpg)


![Version](https://img.shields.io/badge/Version-1.0.0-F21B3F) ![Build](https://img.shields.io/badge/Build-Passing-29BF12) ![Projeto](https://img.shields.io/badge/Projeto-FrasesNerd-08BDBD) ![Code_Quality](https://img.shields.io/badge/Code_Quality-Good-3A5683) ![Languange](https://img.shields.io/badge/Language-Java-F7DF1E) ![Languange](https://img.shields.io/badge/Language-Android-339933)



![Screenshot_2](https://user-images.githubusercontent.com/71250901/109047035-29a80480-76b4-11eb-956e-a6e89eb48137.png)



# Conteúdos
- [Conteúdos](#conteúdos)
    - [Exemplo de uma parte do código](#exemplo-de-uma-parte-do-código)
  - [Instalação](#instalação)
    - [Download](#download)
    - [Instalação e Permissões](#instalação-e-permissões)
  - [Uso](#uso)
  - [Roadmap](#roadmap)
  - [Como contribuir](#como-contribuir)
  - [Licença](#licença)
  - [Status do Projeto](#status-do-projeto)

### Exemplo de uma parte do código
``` JAVA

    public void gerarNovaFrase(View view){                                               
    String[] frases = {//vai gerar frases aleatoriamente                             
            "Vida longa e próspera (Sr. Spock - StarTrek)",                     //0  
            "Eu tenho a força! (He-man)",                                       //1  
            "Que a força esteja com você! (StarWars)",                          //2  
            "Você não passará! (Gandalf)",                                      //3  
            "Com grandes poderes vem grandes responsabilidades (Uncle Ben)",    //4  
            "Ao infinito, e além (BuzzLightyear)" ,                            //5   
            "Bazinga! (Shedon Cooper)" ,                                       //6   
            "Que é que há, velhinho (Pernalonga)"                               //7  
    };                                                                               
       // VAR responsável por gerar os valores aleatórios                            
       // que serão utilizados para indicar a posião no vetor frases                 
           int numero = new Random().nextInt(8);                                     
                                                                                     
      // Mostrando a frase com base na posição sorteada                              
       TextView frase = findViewById(R.id.txtFraseGerada);                           
       frase.setText(frases[numero]);                                                
}// fechamento da função gerarNovaFrase                                              
```

## Instalação 

### Download
Para fazer o download do app [clique aqui](https://github.com/Antonio-Marcos86/App_Frases_Nerd/releases).

### Instalação e Permissões
- Para que a instalação ocorra de forma satisfatória, copie e cole o arquivo .apk em seu celular e abra-o.
- Dê as permissões necessárias.
## Uso

Use esse app para gerar frases de um dos mundos mais legais, o Mundo Nerd.
## Roadmap

Nos próximos dias irei colocar mais frases do mundo nerd.

## Como contribuir

Contribuições são sempre bem-vindas. Existem várias maneiras de contribuir com este projeto, como:

- 💪 Se juntando ao time de desenvolvimento.
- 🌟 Dando uma estrela no projeto.
- 🐛 Reportando um Bug.
- 😅 Indicando um vacilo que eu possa ter cometido.
- 📄 Ajudando a melhorar a documentação.
- 🚀 Compartilhando este projeto com seus amigos.

## Licença

GNU GENERAL PUBLIC LICENSE - Version 3, 29 June 2007

Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
Everyone is permitted to copy and distribute verbatim copies
of this license document, but changing it is not allowed.

Preamble

The GNU General Public License is a free, copyleft license for software and other kinds of works.

The licenses for most software and other practical works are designed to take away your freedom to share and change the works.  By contrast, the GNU General Public License is intended to guarantee your freedom to share and change all versions of a program--to make sure it remains free software for all its users.  We, the Free Software Foundation, use the GNU General Public License for most of our software; it applies also to any other work released this way by its authors.  You can apply it to your programs, too.

## Status do Projeto

Lançamento da versão BETA.


