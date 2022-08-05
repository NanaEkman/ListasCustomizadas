package ads.pdm.listascustomizadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterContatos extends ArrayAdapter<Contato> {

    // atributos:

    // contexto
    private Context context;

    // arraylist de objetos do tipo Contato
    private ArrayList<Contato> contatos;

    public AdapterContatos(Context context, ArrayList<Contato> contatos) {

        // super:
        // contexto, layout do item da lista, arraylist com os itens
        super(context, R.layout.item_lista, contatos);

        // guarda o contexto no atributo correspondente
        this.context = context;

        // guarda o arraylist com os itens no atributo correspondente
        this.contatos = contatos;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        // recuperar um objeto "inflador” de layouts...
        LayoutInflater li = LayoutInflater.from(parent.getContext());

        // "inflando" o xml do item da lista, gerando sua visualização (view)
        View itemView = li.inflate(R.layout.item_lista, parent, false);

        // pegando os objetos gráficos dentro do xml do item da lista
        TextView lblNome = itemView.findViewById(R.id.lblNome);
        TextView lblEmail = itemView.findViewById(R.id.lblEmail);
        TextView lblTelefone = itemView.findViewById(R.id.lblTelefone);

        // colocando dados neste item
        // o objeto esta no índice 'i'
        lblNome.setText(contatos.get(i).getNome());
        lblEmail.setText(contatos.get(i).getEmail());
        lblTelefone.setText(contatos.get(i).getTelefone());

        // a view do item da lista está montada!!
        // é só devolver
        return itemView;
    }

}
