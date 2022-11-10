package com.example.shopping.repository;

import com.example.shopping.entity.Cart;
import com.example.shopping.entity.CartProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {

    /**
     * getting all CartProduct by cartId from cart database
     *
     * @param cartId cart Id
     * @return list of CartProduct
     */
    @Query("select p from CartProduct p where p.cart.cartId = :cartId")
    List<CartProduct> findProductByCartId(@Param("cartId") long cartId);

    /**
     * getting all CartProduct by cartId and productId from cartProduct database
     *
     * @param cartId cart ID
     * @param productId product ID
     * @return CartProduct
     */
    @Query("select p from CartProduct p where p.cart.cartId = :cartId and p.productId = :productId")
    CartProduct findProductByCartIdAAndProductId(@Param("cartId") long cartId , @Param("productId") long productId);

    /**
     * delete CartProduct from cartProduct database by cartId and productId
     *
     * @param cartId cart ID
     * @param productId product ID
     */
    @Transactional
    @Modifying
    @Query("delete from CartProduct p where p.cart.cartId = :cartId and p.productId = :productId")
    public void deleteProductFromCart(@Param("cartId") long cartId, @Param("productId") long productId);
}
