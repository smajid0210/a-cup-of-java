package com.example.api;

import com.example.entity.Blog;
import com.example.entity.BlogDbRepository;
import org.springframework.stereotype.Repository;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MysqlDbRepository implements BlogDbRepository {

    private final DataSource dataSource;

    public DataSource mysqlDataSource() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/Blogproject");
        dataSource.setUser("mysql");
        dataSource.setPassword("1234");

        return dataSource;
    }
    public MysqlDbRepository()
    {
        this.dataSource = mysqlDataSource();
    }
    @Override
    public void createnewblog(Blog blog) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;

        try  {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            String query = "INSERT INTO blogs (title,description,authorid) VALUES (?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getDescription());
            ps.setInt(3, blog.getAuthorid());
            ps.executeUpdate();
            conn.commit();
        }
        catch(SQLException e)
        {
            if(conn!=null)
            {
                try {
                    conn.rollback();
                }
                catch (SQLException sq) {
                    sq.printStackTrace();
                }
            }
        }

        finally
        {
            if(conn!=null)
            {
                conn.close();
            }
            if(ps!=null)
            {
                ps.close();
            }


        }
    }

    @Override
    public void deleteblog(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try  {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            String query = "DELETE from blogs WHERE blogid = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();
            conn.commit();
        }
        catch (SQLException e) {
            if(conn!= null)
            {
                try {
                    conn.rollback();
                }
                catch (SQLException sq)
                {
                    sq.printStackTrace();
                }
            }
        }

        finally
        {
            if(conn!=null)
            {
                conn.close();
            }
            if(ps!=null)
            {
                ps.close();
            }
        }
    }

    @Override
    public void editblog(Blog blog) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try  {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
            String query = "UPDATE blogs SET title = ?, description = ?, authorid = ?, updated_at=CURRENT_TIMESTAMP WHERE blogid = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, blog.getTitle());
            ps.setString(2, blog.getDescription());
            ps.setInt(3, blog.getAuthorid());
            ps.setInt(4, blog.getBlogid());
            ps.executeUpdate();
            conn.commit();
        }

        catch(SQLException e)
        {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }
            catch(SQLException sq)
            {
                sq.printStackTrace();
            }
        }

        finally
        {
            if(conn!=null)
            {
                conn.close();
            }
            if(ps!=null)
            {
                ps.close();
            }
        }
    }

    @Override
    public List<Blog> viewallblogs() throws SQLException {
        List<Blog> bloglist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try  {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM BLOGS ORDER BY blogid DESC";
            ps = conn.prepareStatement(query);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Blog blog = new Blog(rs.getInt("blogid"),rs.getString("title"),rs.getInt("authorid"));
                    bloglist.add(blog);

                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if(conn!=null)
            {
                conn.close();
            }
            if(ps!=null)
            {
                ps.close();
            }
            return bloglist;
        }
    }

    public Blog viewablogbyid(int id) throws SQLException {

        Blog blog = new Blog();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            String query = "SELECT * FROM BLOGS WHERE blogid = ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()) {

                while(rs.next()) {
                    blog.setBlogid(rs.getInt("blogid"));
                    blog.setTitle(rs.getString("title"));
                    blog.setDescription(rs.getString("description"));
                    blog.setAuthorid(rs.getInt("authorid"));
                }
                System.out.println(blog.getTitle());
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(conn!=null)
            {
                conn.close();
            }
            if(ps!=null)
            {
                ps.close();
            }
            return blog;
        }

    }
}
