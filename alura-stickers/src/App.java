import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {
	public static void main(String[] args) throws Exception {
		// Fazer uma conexao HTTP e buscar os top 250 filmes
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/dados.properties");
		props.load(file);

		String url = props.getProperty("prop.url");

		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// Extrair só os dados que interessam (titulo, poster, classificação)
		JsonParser parser = new JsonParser();

		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// Exibir e manipular os dados
		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println("Rank: " + filme.get("rank"));
			System.out.println("Titulo: " + filme.get("title"));
			System.out.println("Poster: " + filme.get("image"));
			System.out.println("Classificação: " + filme.get("imDbRating"));
			double number = Double.parseDouble(filme.get("imDbRating"));

			for (int i = 1; i <= number; i++) {
				System.out.print("\u2B50");
			}

			System.out.println();
			System.out.println();

		}

	}

}
