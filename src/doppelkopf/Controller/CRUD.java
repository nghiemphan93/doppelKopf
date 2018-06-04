package doppelkopf.Controller;

import doppelkopf.Model.CardModel.Card;
import doppelkopf.Model.PlayerModel.Player;

import java.sql.*;
import java.util.ArrayList;

public class CRUD {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    String dbUrl = "jdbc:mysql://localhost:3306/doppelkopf?useSSL=false";
    String user = "student";
    String pass = "student";

    /**
     * Insert all new cards to database
     * @param listCards
     */
    public void insertAllCardsToDatabase(ArrayList<Card> listCards){
        try {
            // get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            // create statement
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO CARD (name, suit, rank, strength, point, FehlOrNot) VALUES (?,?,?,?,?,?)");

            // insert a new employee
            System.out.println("Saving all cards to database");

            for(Card card : listCards){
                preparedStatement.setString(1, card.getName());
                preparedStatement.setString(2, card.getSuit());
                preparedStatement.setString(3, card.getRank());
                preparedStatement.setString(4, card.getStrength());
                preparedStatement.setInt(5, card.getPoint());
                if(card.isFehl()){
                    preparedStatement.setInt(6, 1);
                }else{
                    preparedStatement.setInt(6, 0);
                }

                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }   // end of insertAllCardsToDatabase

    /**
     * Insert card played in a round to database
     * @param player
     * @param card
     */
    public void insertCardPlayed(Player player, Card card){
        try {
            // get card_ID and player_ID
            int card_ID = selectCardID(card);
            int player_ID = selectPlayerID(player);

            // get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            // create statement
            String generatedColumns[] = { "ID" };
            preparedStatement = connection.prepareStatement("INSERT INTO cardsplayed(player_id, card_id) VALUES (?,?)", generatedColumns);

            // set parameters
            preparedStatement.setInt(1, player_ID);
            preparedStatement.setInt(2, card_ID);

            // execute statement
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }   // end of insertCardPlayed

    /**
     *  Insert cards collected in one game to database
     * @param player
     * @param cards
     */
    public void insertCardsCollected(Player player, ArrayList<Card> cards){
        try {


            // get card_ID and player_ID
            for(Card card : cards){
                int card_ID = selectCardID(card);
                int player_ID = selectPlayerID(player);

                // get connection to db
                connection = DriverManager.getConnection(dbUrl, user, pass);
                // create statement
                String generatedColumns[] = { "ID" };
                preparedStatement = connection.prepareStatement("INSERT INTO cardscollected(player_id, card_id) VALUES (?,?)", generatedColumns);

                // set parameters
                preparedStatement.setInt(1, player_ID);
                preparedStatement.setInt(2, card_ID);

                // execute statement
                preparedStatement.executeUpdate();

                resultSet = preparedStatement.getGeneratedKeys();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }   // end of insertCardPlayed

    /**
     * Return card_ID from database given the card object
     * @param card
     * @return
     */
    public int selectCardID(Card card){
        int card_ID = 0;

        try{
            // get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            // create statement
            String formatedStatment = String.format("SELECT card_ID FROM card WHERE name = '%s'", card.getName());
            preparedStatement = connection.prepareStatement(formatedStatment);

            // execute statement
            resultSet = preparedStatement.executeQuery();

            // get data back
            resultSet.next();
             card_ID= Integer.parseInt(resultSet.getString("card_id"));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return card_ID;
    }   // end of selectCardID

    /**
     * Return player_ID from database given the player object
     * @param player
     * @return
     */
    public int selectPlayerID(Player player){
        int player_ID = 0;

        try{
            // get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            // create statement
            String formatedStatment = String.format("SELECT player_ID FROM player WHERE name = '%s'", player.getName());
            preparedStatement = connection.prepareStatement(formatedStatment);


            // execute statement
            resultSet = preparedStatement.executeQuery();

            // get data back
            resultSet.next();
            player_ID= Integer.parseInt(resultSet.getString("player_id"));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return player_ID;
    }   // end of selectCardID

    /**
     * Insert new game to database, return the game_ID
     * @return
     */
    public int insertNewGame(ArrayList<Player> players){
        int game_ID = 0;
        try {
            // get all players_ID from database
            ArrayList<Integer> players_ID = new ArrayList<>();
            for(Player player : players){
                players_ID.add(selectPlayerID(player));
            }

            // get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            // create statement
            String generatedColumns[] = { "ID" };
            preparedStatement = connection.prepareStatement("INSERT INTO game(player1_ID, player2_ID, player3_ID, player4_ID) VALUES (?,?,?,?)", generatedColumns);

            // set parameters
            preparedStatement.setInt(1, players_ID.get(0));
            preparedStatement.setInt(2, players_ID.get(1));
            preparedStatement.setInt(3, players_ID.get(2));
            preparedStatement.setInt(4, players_ID.get(3));


            // execute statement
            preparedStatement.executeUpdate();

            // get the game_ID back to return
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            game_ID = resultSet.getInt(1);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return game_ID;
    }   // end of insertNewGame

    /**
     * Insert a all infor of the game played by one player to database
     */
    public void insertGamePlayed(Player player, Player partner, int game_ID){
        try {
            // get all players_ID from database
            int player_ID = selectPlayerID(player);
            int partner_ID = selectPlayerID(partner);

            // get connection to db
            connection = DriverManager.getConnection(dbUrl, user, pass);

            // create statement
            String generatedColumns[] = { "ID" };
            preparedStatement = connection.prepareStatement("INSERT INTO gamesplayed (hadKreuzQueen, pointsWonInGame, wonOrNot, player_ID, partnerInGame_ID, game_ID) VALUES (?,?,?,?,?,?)", generatedColumns);

            // set parameters
            // hadKreuzQueen
            if(player.hasKreuzQueen()){
                preparedStatement.setInt(1, 1);
            }else{
                preparedStatement.setInt(1, 0);
            }

            // points won in game
            preparedStatement.setInt(2, player.getPointsWonPerGame());

            // won or not
            if(player.isGameWon()){
                preparedStatement.setInt(3, 1);
            }else{
                preparedStatement.setInt(3, 0);
            }

            // player_ID, partner_ID, game_ID
            preparedStatement.setInt(4, player_ID);

            // if there's no partner || partner_ID == 0  -> partner = null
            if(partner_ID == 0){
                preparedStatement.setNull(5, 2);
            }else{
                preparedStatement.setInt(5, partner_ID);
            }

            preparedStatement.setInt(6, game_ID);

            // execute statement
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
