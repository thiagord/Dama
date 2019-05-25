package estrutura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Lista {
		
	private JogoDeCartas inicio;
	
	public Lista() {
		this.inicio = null;
	}
	
     //M�todo para verificar se est� vazia
	public boolean isEmpty() {
		return (this.inicio == null);
	}
	
	//M�todo para verificar se resta apenas um elemento na lista
	public boolean resta1() {
		JogoDeCartas tmp, tmp2;
		tmp = inicio;
		tmp2 = tmp.getProximo();	
		return(tmp.getElemento() == tmp2.getElemento());		
	}

	//M�todo para inserir elemento na lista
	public void inserirJogador(Object elemento) {
		JogoDeCartas novo = new JogoDeCartas();
		novo.setElemento(elemento);
		
		//Caso especial em que a lista est� vazia e ambos anterior e pr�ximo apontam o pr�prio elemento
		if(isEmpty()) {
			novo.setAnterior(novo);
			novo.setProximo(novo);
			inicio = novo;
		}else {
			
       JogoDeCartas tmp = inicio;
       while(tmp.getProximo() != inicio) {
    	   tmp = tmp.getProximo();
       }
       inicio.setAnterior(novo);
       tmp.setProximo(novo);
       novo.setAnterior(tmp);
       novo.setProximo(inicio);
			
		}
		
	}
	
	//M�todo para remover elemento
	public void removerJogador(Object elemento) {
		
		JogoDeCartas tmp, tmp_ant, tmp_pro;
		tmp = inicio;
		
		do {
		
		if(tmp.getElemento() == elemento) {
		
			if(inicio.getElemento() == elemento) {
 			inicio = inicio.getProximo();
			}
					
			tmp_ant = tmp.getAnterior();
			tmp_pro = tmp.getProximo();
			
			tmp_ant.setProximo(tmp_pro);
			tmp_pro.setAnterior(tmp_ant);
			
			System.out.println("\nJogador eliminado:" +tmp.getElemento());
			}
     
		tmp = tmp.getProximo();	
		
	}while(tmp.getProximo() != inicio.getProximo());
		
		}
		
	
	//M�todo para exibir elementos
	public void exibirJogadores() {
		
		JogoDeCartas tmp;
		tmp = inicio;
		
		System.out.print("\nJogadores da rodada: ");
		do {
			System.out.print(" ["+tmp.getElemento()+"] ");
			tmp = tmp.getProximo();
		}while(tmp != inicio);
		
	}

	
	//M�todo para jogar uma partida
public void Jogar() {

	
	//Lista para gerar carta aleat�ria
    List<Integer> givenList = Arrays.asList(1, 3, 9, 12);

		JogoDeCartas tmp, tmp2;
		tmp = inicio;
		int pular1 = 0, sentido = 0;
	
		
		while(!resta1()) {
			
			//Gerando uma carta aleat�ria tirada da lista
		    Random rand = new Random();
		    int carta = givenList.get(rand.nextInt(givenList.size()));		 
			
	        //Jogadores ainda no jogo
		    System.out.println("----------------------------------------------------------/----------------------------------------------------------");
		    exibirJogadores();
		    //IF pra verificar o sentido da lista
			if(sentido == 0) {
				System.out.print(" Sentido da roda ---> ");
			}else {
				System.out.print(" Sentido da roda <--- ");
			}
		    System.out.print("\n \nJogando agora: "+tmp.getElemento());

		    
				
		    switch (carta) {
	        //Pula o pr�ximo jogador e passa a vez para o seguinte. 
			    case 1:
			    	
			   pular1 = 1;
			   System.out.println("\n\n"+tmp.getElemento()+" tirou a carta 1, pula o pr�ximo jogador a pessa a vez para o seguinte!");
			    break;
            
            //Inverte o sentido do jogo.
		    case 12:
		    		 
			System.out.println("\n\n"+tmp.getElemento()+" tirou a carta 12, inverteu o sentido do jogo!");
			if(sentido == 0) {
				sentido = 1;
			}else if(sentido == 1) {
				sentido = 0;
			}
	        break;
	        
            //Remove o terceiro jogador contado a partir do atual
		    case 3:
		    
			System.out.println("\n\n"+tmp.getElemento()+" tirou a carta 3, remove o terceiro jogador contado a partir do atual!");
		   
			if(sentido == 0) {
				
		    tmp2 = tmp.getProximo();
		    tmp2 = tmp2.getProximo();
		    tmp2 = tmp2.getProximo();
		    
			}else {
				
			tmp2 = tmp.getAnterior();
			tmp2 = tmp2.getAnterior();
			tmp2 = tmp2.getAnterior();	
			}
		    	    	
		    removerJogador(tmp2.getElemento());
	        break;
	        
            //Elimina o jogador anterior na roda. 
		    case 9:
		    		
	    	 System.out.println("\n\n"+tmp.getElemento()+" tirou a carta 9, elimina o jogador anterior na roda!");
		 
	    	 
	    	 //IF para verificar o sentido da roda
	    	 if(sentido == 0) {
				
			    tmp2 = tmp.getAnterior();
			    
				}else {
					
				tmp2 = tmp.getProximo();

				}
		    removerJogador(tmp2.getElemento());
	 		

	        break;
	        
		    default:
		        System.out.println("Carta invalida!");
 
		  }
		    //Fim do SWITCH
		    
			//Passa o turno para o pr�ximo jogador 
		    //if para verificar o sentido da turma
		    if(sentido == 0) {
			tmp = tmp.getProximo();
		    }else {
		    tmp = tmp.getAnterior();	
		    }
		
		  //Verifica se h� um ganhador
			if(resta1()) {
				 System.out.println("\n\n----------------------------------------------------------/FIM DE JOGO/----------------------------------------------------------");				
				System.out.println("\n ["+tmp.getElemento()+"] � O VENCEDOR!");
				 System.out.println("----------------------------------------------------------/FIM DE JOGO/----------------------------------------------------------\n\n\n");
				
	     		}	
			
		//Se o jogador tirou a carta 2 pula mais 1
		if(pular1 == 1) {
			//IF pra verificar o sentido da turma
		    if(sentido == 0) {
			tmp = tmp.getProximo();
		    }else {
		    tmp = tmp.getAnterior();	
		    }
			pular1 = 0;
			}
		}		
	}
		
	public static void main(String[] args) {

		Lista lista = new Lista();
	
		//Adiciona os 10 jogadores
		lista.inserirJogador("Thiago");
		lista.inserirJogador("Amanda");
		lista.inserirJogador("Gabriel");
		lista.inserirJogador("Jeronimo");
		lista.inserirJogador("Lorran");
		lista.inserirJogador("Zaiko");
		lista.inserirJogador("Rafael");
		lista.inserirJogador("Kenji");
		lista.inserirJogador("Joeh");
		lista.inserirJogador("Luciene");
        //Jogar a partida
		lista.Jogar();
				
	}
 }