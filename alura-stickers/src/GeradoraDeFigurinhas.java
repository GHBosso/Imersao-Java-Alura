import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
	
	public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
		
		//leitura da imagem
		//InputStream inputStream = new FileInputStream(new File("entrada/Filme01.jpg"));
		/*InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
		*/
		BufferedImage imagemOriginal = ImageIO.read(inputStream);
		
		//cria nova imagem em memória com transparencia e tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		
		
		//copiar a imagem original pra nova imagem(em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal,0,0, null);
		
		//configurar a fonte
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(font);
		
		//escrever uma frase na nova imagem
		graphics.drawString("TOPZERA", 168, novaAltura - 100);
		
		//escrever a nova imagem em arquivo
		ImageIO.write(novaImagem, "png", new File(nomeArquivo));
	}
}
	
