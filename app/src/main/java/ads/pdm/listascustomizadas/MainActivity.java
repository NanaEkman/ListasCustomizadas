package ads.pdm.listascustomizadas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // atributos referentes aos objetos gráficos
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtTelefone;
    private Button btnAdiciona;
    private ListView listaContatos;

    // ArrayList de contatos
    private ArrayList<Contato> contatos = new ArrayList<>();

    // adapter da lista
    private AdapterContatos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ligando atributos com os ID dos objetos na interface
        txtNome = findViewById( R.id.txtNome );
        txtEmail = findViewById( R.id.txtEmail );
        txtTelefone = findViewById( R.id.txtTelefone );
        btnAdiciona = findViewById( R.id.btnAdiciona );
        listaContatos = findViewById( R.id.listaContatos );

        // criando e associando escutador do botão
        btnAdiciona.setOnClickListener( new EscutadorBotao() );

        // configurando a lista:

        // criando adaptador
        adaptador = new AdapterContatos( this, contatos );

        // associando o adaptador a lista
        listaContatos.setAdapter( adaptador );

        // Criando o objeto escutador de cliques na lista:
        EscutadorLista el = new EscutadorLista();
        // Configurar a lista com o escutador de cliques comuns:
        listaContatos.setOnItemClickListener( el );
        // configurar a lista com o escutador de cliques longos
        listaContatos.setOnItemLongClickListener( el );

    }

    // classe interna do escutador do clique no botão adiciona
    private class EscutadorBotao implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            // variaveis para auxilio
            String nome;
            String email;
            String telefone;

            // pegando dados nas caixas de texto
            nome = txtNome.getText().toString();
            email = txtEmail.getText().toString();
            telefone = txtTelefone.getText().toString();

            // criando objeto Contato
            Contato c = new Contato( nome, email, telefone );

            // inserindo no ArrayList
            contatos.add( c );

            // avisando o adapter que os dados foram atualizados
            adaptador.notifyDataSetChanged();

            // "limpando" a interface, para a próxima digitação
            txtNome.setText("");
            txtEmail.setText("");
            txtTelefone.setText("");
        }
    }

    // Criando a classe escutadora de cliques na lista:
    private class EscutadorLista implements AdapterView.OnItemClickListener,
                                        AdapterView.OnItemLongClickListener {
        // Método que vai ser chamado no caso de clique simples:

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // O parâmetro i é o número do item clicado ...

            // Pegando o objeto correto no Arraylist:
            Contato c = contatos.get(i);

            // Montando uma string com os dados do objeto:
            String msg = c.getNome() + "\n";
            msg = msg + c.getEmail() + "\n";
            msg = msg + c.getTelefone();

            // Exibindo um toast:
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }

        // Método que vai ser chamado no caso de clique longo:

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Remover o item de índice i do ArrayList:
            contatos.remove( i );
            // Avisar o adapter que o arraylist mudou:
            adaptador.notifyDataSetChanged();
            // mensagem informativa
            Toast.makeText(MainActivity.this, "Item apagado!", Toast.LENGTH_LONG).show();
            // receita de bolo: retornar true, indicando que o evento foi tratado.
            // se retornar false, vai querer tratar clique comum também
            return true;
        }
    }

}