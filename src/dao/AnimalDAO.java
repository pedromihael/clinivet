package dao;

import modelo.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AnimalDAO extends DAO {

    static Connection conn = null;
    static ResultSet rs = null;
    static StringBuilder sql = new StringBuilder();

    /*
     * executeBooleanQuery: retorna true se a operacao for realizada com sucesso, false caso contrario.
     * @param sql StringBuilder
     * @return
     * */
    private static boolean executeBooleanQuery(StringBuilder sql) throws SQLException {
        try {
            conn = getInstance();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
        }  catch (Exception e) {
            return false;
        } 
        finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }

    /*
    * recuperaAnimal: retorna animal com id requerido.
    * @param id Int
    * @return
    * */
    public Animal recuperar(int animalId) throws SQLException, ClassNotFoundException, NaoEncontradoExeception {

        Animal animal = null;
        sql.append("SELECT * ");
        sql.append("FROM animais");
        sql.append("WHERE id =  ?");

        try {

            //Cria instancia da conexão (usando singleton)
            //Executa query com o sql escrito acima
            conn = getInstance();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, animalId);
            return ps.executeQuery();
    }

    /*
     * cadastraAnimal: insere um animal no banco de dados.
     * @param animal Animal
     * @return
     * */
    public boolean cadastrar(Animal animal) throws SQLException {
        sql.append("INSERT INTO animais");
        sql.append("(nome, sexo, cor, raca, donoId) ");
        sql.append("VALUES ("+
                "'"+animal.getNome()+ "'" + ", " +
                "'"+animal.getSexo()+"'" + ", " +
                "'"+animal.getCor()+"'" + ", " +
                "'"+animal.getRaca()+"'" + ", " +
                "'"+animal.getDonoId() +"'"+
                ");");

        return executeBooleanQuery(sql);
    }

    /*
     * alteraAnimal: altera um campo de um determinado animal.
     * @param int animalId
     * @param String coluna
     * @param String novoValor
     * @return
     * */
    public boolean alterar(Animal animal) throws SQLException {

        sql.append("UPDATE animais SET ");
        sql.append("nome = '" + animal.getNome() + "', ");
        sql.append("sexo = '" + animal.getSexo() + "', ");
        sql.append("cor = '" + animal.getCor() + "', ");
        sql.append("raca = '" + animal.getRaca() + "', ");
        sql.append("donoId = '" + animal.getDonoId() + "' ");
        sql.append("WHERE id = '" + animal.getId() + "'");

        return executeBooleanQuery(sql);
    }

    /*
     * removeAnimal: remove animal do banco de dados.
     * @param String animalId
     * @return
     * */
    public boolean remover(int animalId) throws SQLException {

        sql.append("DELETE FROM animais ");
        sql.append("WHERE id = " + animalId);

        return executeBooleanQuery(sql);
    }

}
