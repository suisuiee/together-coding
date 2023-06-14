package com.example.myspringbootproject.domain.post.repository;

import com.example.myspringbootproject.domain.post.dto.PostDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardJdbcRepository extends JdbcDaoSupport implements BoardRepository {
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public BoardJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        setDataSource(dataSource);
    }

    @Override
    public boolean createPost(PostDTO postDTO) {
        String sql = "insert into TBL_POST(user_id, title, content, attachment, ctg_id) " +
            "values ((?), (?), (?), (?), (?))";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, postDTO.getUserId());
            pstmt.setString(2, postDTO.getTitle());
            pstmt.setString(3, postDTO.getContent());
            pstmt.setString(4, postDTO.getAttachment());
            pstmt.setInt(5, postDTO.getCtgId());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    postDTO.setId(id);
                } else {
                    throw new SQLException("게시물 작성에 실패했습니다. 게시물의 ID를 받지 못했습니다.");
                }
            }

            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;

        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return false;
    }

    @Override
    public List<PostDTO> findAllPosts() {
        String sql = "SELECT * FROM tbl_post 리밋 ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<PostDTO> posts = new ArrayList<>();
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeQuery();

            rs = pstmt.getResultSet();

            while (rs.next()) {
                PostDTO post = new PostDTO(

                );

                post.setId(rs.getInt("id"));
                post.setContent(rs.getString("title"));
                post.setTitle(rs.getString("content"));
                post.setAttachment(rs.getString("attachment"));
                post.setIsVisible(rs.getInt("is_visible"));
                post.setCtgId(rs.getInt("ctg_id"));
                post.setCreatedAt(rs.getDate("created_at"));
                post.setUpdatedAt(rs.getDate("updated_at"));
                post.setUserId(rs.getInt("user_id"));

                posts.add(post);
            }

        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return posts;
    }

    @Override
    public PostDTO findPostById(int id) {
        String sql = "SELECT * FROM tbl_post WHERE id = (?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, id);
            pstmt.executeQuery();

            rs = pstmt.getResultSet();

            while (rs.next()) {
                PostDTO post = new PostDTO();

                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setAttachment(rs.getString("attachment"));
                post.setIsVisible(rs.getInt("is_visible"));
                post.setCtgId(rs.getInt("ctg_id"));
                post.setCreatedAt(rs.getDate("created_at"));
                post.setUpdatedAt(rs.getDate("updated_at"));
                post.setUserId(rs.getInt("user_id"));

                return post;
            }

        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return null;
    }

    @Override
    public boolean updatePost(PostDTO postDTO) {
        String sql = "UPDATE tbl_post "
            + "SET "
            + "title = (?), "
            + "content = (?), "
            + "attachment = (?), "
            + "ctg_id = (?) "
            + "WHERE id = (?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, postDTO.getTitle());
            pstmt.setString(2, postDTO.getContent());
            pstmt.setString(3, postDTO.getAttachment());
            pstmt.setInt(4, postDTO.getCtgId());
            pstmt.setLong(5, postDTO.getId());

            pstmt.executeUpdate();

            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;

        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pstmt);
        }
        return false;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (conn != null) {
            close(conn);
        }
    }
    private void close(Connection conn, PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (conn != null) {
            close(conn);
        }
    }
    private void close(Connection conn) {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

}
