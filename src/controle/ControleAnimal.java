package controle;
import modelo.Animal;
import modelo.Cliente;
import dao.AnimalDAO;
import dao.ClienteDAO;
import dao.NaoEncontradoExeception;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControleAnimal {
    public Animal animal;
    private ResultSet resultSet;

    public boolean adicionaAnimal(String nome, int proprietario, String nascimento, int especie, int raca, String sexo, String cor) {
        Animal animal = new Animal(0, nome, proprietario, nascimento, especie, raca, sexo, cor);

        try{
        	if (AnimalDAO.cadastrar(animal)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
    }
    
    public boolean alteraAnimal(int codigo, String nome, int proprietario, String nascimento, int especie, int raca, String sexo, String cor) {
        Animal animal = new Animal(codigo, nome, proprietario, nascimento, especie, raca, sexo, cor);

        try {
            if (AnimalDAO.alterar(animal)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }
    
    public boolean removeAnimal(int codigo) {
        try {
            if (AnimalDAO.remover(codigo)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

    public Animal buscaAnimal(int codigo) {
        Animal animal = null;
        ResultSet rs;

        try {
            rs = AnimalDAO.recuperar(codigo);
            while (rs.next()) {
                animal = new Animal(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("id_dono"), rs.getString("data_nascimento"), rs.getInt("id_especie"), rs.getInt("id_raca"), rs.getString("sexo"), rs.getString("cor"));
            }
        } catch (ClassNotFoundException | SQLException | NaoEncontradoExeception e) {
            return null;
        }

        if (animal == null)
            return null;

        return animal;
    }
}