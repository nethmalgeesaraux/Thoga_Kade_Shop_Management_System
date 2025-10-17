package dao;

import modle.DTO.ItemDTO;
import modle.entity.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public boolean saveItem(ItemDTO item) throws SQLException {
        String sql = "INSERT INTO item (item_code, description, category, qty_on_hand, unit_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, item.getItemCode());
            pstm.setString(2, item.getDescription());
            pstm.setString(3, item.getCategory());
            pstm.setInt(4, item.getQtyOnHand());
            pstm.setDouble(5, item.getUnitPrice());

            return pstm.executeUpdate() > 0;
        }
    }

    public List<ItemDTO> getAllItems() throws SQLException {
        List<ItemDTO> items = new ArrayList<>();
        String sql = "SELECT * FROM item";

        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ItemDTO item = new ItemDTO(
                        rs.getString("item_code"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("qty_on_hand"),
                        rs.getDouble("unit_price")
                );
                items.add(item);
            }
        }
        return items;
    }

    public boolean updateItem(ItemDTO item) throws SQLException {
        String sql = "UPDATE item SET description=?, category=?, qty_on_hand=?, unit_price=? WHERE item_code=?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, item.getDescription());
            pstm.setString(2, item.getCategory());
            pstm.setInt(3, item.getQtyOnHand());
            pstm.setDouble(4, item.getUnitPrice());
            pstm.setString(5, item.getItemCode());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean deleteItem(String itemCode) throws SQLException {
        String sql = "DELETE FROM item WHERE item_code = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, itemCode);
            return pstm.executeUpdate() > 0;
        }
    }

    public ItemDTO searchItem(String itemCode) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_code = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, itemCode);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return new ItemDTO(
                        rs.getString("item_code"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("qty_on_hand"),
                        rs.getDouble("unit_price")
                );
            }
            return null;
        }
    }

    public boolean updateItemQuantity(String itemCode, int newQuantity) throws SQLException {
        String sql = "UPDATE item SET qty_on_hand = ? WHERE item_code = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setInt(1, newQuantity);
            pstm.setString(2, itemCode);

            return pstm.executeUpdate() > 0;
        }
    }
}