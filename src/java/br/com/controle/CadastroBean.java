package br.com.controle;

import br.com.DAO.CadastroDAO;
import br.com.modelo.Cadastro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "bean")
@SessionScoped
public class CadastroBean implements Serializable {

    private Cadastro cadastro;
    private CadastroDAO dao;
    private String message;
    private List<Cadastro> listaJG;
    //private String id;

    public CadastroBean() {
        cadastro = new Cadastro();
        dao = new CadastroDAO();
        listaJG = new ArrayList<>();
    }

    //Get e Set

    public Cadastro getCadastro() {
        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Cadastro> getListaJG() {
        return listaJG;
    }

    public void setListaJG(List<Cadastro> listaJG) {
        this.listaJG = listaJG;
    }

   /* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/
    
    

    /**
     * ***********
     */
    public void salvar() {
        dao.salvar(cadastro);
        listaJG.add(cadastro);
        cadastro = new Cadastro();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Dados salvos com sucesso "));
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Dadoo editados", ((Cadastro) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        dao.editar((Cadastro) event.getObject());
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Cadastro) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    
    public void deletar(Cadastro jg) {
        listaJG.remove(jg);
        dao.deletar(jg);

    }

  
    public void selecionar(){
        cadastro = dao.selecionar(cadastro.getId());
    }

}
