package com.oldMan.dao;

import com.oldMan.bean.PublicNotice;
import com.oldMan.util.DBUtil;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/3 14:08
 */
import java.sql.*;
import java.util.List;

public class PublicNoticeDao {
    public List<PublicNotice> getAllPublicNotice() {
        List<PublicNotice> noticeList = new ArrayList<>();

        // 连接数据库，执行查询等操作，将查询结果转换为 PublicNotice 对象列表
        // 示例代码如下（请根据您的实际情况调整）
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public_notice");

            while (resultSet.next()) {
                PublicNotice notice = new PublicNotice();
                notice.setNoticeId(resultSet.getInt("notice_id"));
                notice.setTitle(resultSet.getString("title"));
                notice.setContent(resultSet.getString("content"));
                notice.setCreateTime(resultSet.getTimestamp("created_at").toLocalDateTime());
                notice.setNoticeType(resultSet.getInt("notice_type"));

                noticeList.add(notice);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }

        return noticeList;
    }

    public Map<Integer, List<PublicNotice>> getAllPublicNoticeGroupedByType() {
        Map<Integer, List<PublicNotice>> groupedNoticeList = new HashMap<>();

        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public_notice");

            while (resultSet.next()) {
                PublicNotice notice = new PublicNotice();
                notice.setNoticeId(resultSet.getInt("notice_id"));
                notice.setTitle(resultSet.getString("title"));
                notice.setContent(resultSet.getString("content"));
                notice.setCreateTime(resultSet.getTimestamp("created_at").toLocalDateTime());
                notice.setNoticeType(resultSet.getInt("notice_type"));

                int noticeType = notice.getNoticeType();
                groupedNoticeList.computeIfAbsent(noticeType, k -> new ArrayList<>()).add(notice);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }

        return groupedNoticeList;
    }


    public boolean addPublicNotice(PublicNotice notice) {
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "INSERT INTO public_notice (title, content, created_at, notice_type) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            LocalDateTime currentTime = LocalDateTime.now();
            Timestamp currentTimestamp = Timestamp.valueOf(currentTime);

            pstmt.setString(1, notice.getTitle());
            pstmt.setString(2, notice.getContent());
            pstmt.setTimestamp(3, currentTimestamp);
            pstmt.setInt(4, notice.getNoticeType());

            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return false;
    }
    public boolean deletePublicNotice(int noticeId) {
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "DELETE FROM public_notice WHERE notice_id=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, noticeId);

            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return false;
    }
}
