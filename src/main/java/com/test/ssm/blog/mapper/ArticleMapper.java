package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ArticleMapper {

    //根据id删除
    Integer deleteById(Integer articleId);


    //添加文章
    Integer insert(Article article);

    //更新文章
    Integer update(Article article);

    //获得所有文章
    List<Article> findAll(HashMap<String,Object> criteria);

    //文章归档
    List<Article> listAllNotWithContent();

    //获取文章总数
    Integer countArticle(@Param(value = "status") Integer status);

    //获取留言总数
    Integer  countArticleComment();

    //获取浏览量总数
    Integer countArticleView();

    //获取所有文章（文章归档）
    List<Article> listArticle();

    //根据id查询用户信息
    Article getArticleByStatusAndId(@Param(value = "status") Integer status,@Param(value = "id")Integer id);

    //根据ID查找
    Article getArticleById(@Param(value = "id")Integer id);

    /**分页操作（这个需要学习）
     * status 状态
     * pageIndex 从第几页开始
     * pageSize 数量
     */
    //不鼓励使用
    @Deprecated
     List<Article> pageArticle(@Param(value = "status") Integer status,
                               @Param(value = "pageIndex") Integer pageIndex,
                               @Param(value = "pageSize") Integer pageSize);

    /**
     * 获得访问最多的文章(猜你喜欢)
     * limit 查询数量
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);

    //获得上一篇文章
    Article getAfterArticle(@Param(value = "id") Integer id);

    //获得下一篇文章
    Article getPreArticle(@Param(value = "id") Integer id);

    /**获得随机文章
     * @param limit 查询数量
     * @return
     */

    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    //热评文章
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);

    //更新文章评论数
    void updateCommentCount(@Param(value = "articleId")Integer articleId);

    //获得最后更新的记录
    Article getLastUpdateArticle();

    //用户的文章数
    Integer countArticleByUser(@Param(value = "id")Integer id);

    //根据分类ID
    List<Article> findArticleByCategoryId(@Param(value = "categoryId")Integer categoryId,
                                         @Param("limit") Integer limit );

    //根据分类ID集合
    List<Article> findArticleByCategoryIds(@Param(value = "categoryIds")List<Integer> categoryIds,
                                          @Param("limit") Integer limit );


    //获得最新文章
    List<Article> listArticleByLimit(Integer limit);

    /**批量删除文章
     *  @param ids 文章Id列表
     */
    Integer deleteBatch(@Param(value = "ids")List<Integer> ids);


    //根据用户查询文章列表ID
    List<Integer> selectArticleIdByUserId(Integer articleUserId);


}
