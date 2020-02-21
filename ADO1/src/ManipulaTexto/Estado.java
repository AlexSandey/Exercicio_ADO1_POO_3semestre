/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManipulaTexto;

/**
 *
 * @author alex.sssouza7
 */
public class Estado {
    
    private String estado;
    private float pib;
    private String regiao;
    
    public Estado(String estado, float pib, String regiao){
        this.estado = estado;
        this.pib = pib;
        this.regiao = regiao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getPib() {
        return pib;
    }

    public void setPib(float pib) {
        this.pib = pib;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
   

}
