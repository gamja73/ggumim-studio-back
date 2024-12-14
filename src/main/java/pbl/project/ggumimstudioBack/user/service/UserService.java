package pbl.project.ggumimstudioBack.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;
import pbl.project.ggumimstudioBack.user.dto.request.ProductShoppingCartListRequestDto;
import pbl.project.ggumimstudioBack.user.dto.request.ProductShoppingCartRequestDto;
import pbl.project.ggumimstudioBack.user.dto.request.ProductWishRequestDto;
import pbl.project.ggumimstudioBack.user.dto.response.ProductShoppingCartListResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageResponseData;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageShoppingCartResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageWishListResponseDto;
import pbl.project.ggumimstudioBack.user.entity.ShoppingCart;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.entity.WishList;
import pbl.project.ggumimstudioBack.user.repository.ShoppingCartRepository;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;
import pbl.project.ggumimstudioBack.user.repository.WishListRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserMyPageResponseData myPage()
    {
        Long userUID = jwtUtil.getUserUID();
        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return new UserMyPageResponseData(target);
    }

    public PaginationResponse<UserMyPageWishListResponseDto> myPageWishList(BaseSearchParamRequestDto requestDto)
    {
        Long userUID = jwtUtil.getUserUID();

        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return wishListRepository.myPageWishList(target, requestDto);
    }

    @Transactional
    public String productWish(ProductWishRequestDto requestDto)
    {
        Long userUID = jwtUtil.getUserUID();

        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        Product wishProduct = productRepository.findById(requestDto.getProductUID())
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        WishList wishList = wishListRepository.findProductWish(target, wishProduct);

        if (wishList == null)
        {
            wishListRepository.save(new WishList(target, wishProduct));
            return "추가되었습니다.";
        }
        else
        {
            wishList.delete();
            return "삭제되었습니다.";
        }
    }

    public List<UserMyPageShoppingCartResponseDto> myPageShoppingCart()
    {
        Long userUID = jwtUtil.getUserUID();

        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return shoppingCartRepository.myPageShoppingCart(target);
    }

    @Transactional
    public String productShoppingCart(ProductShoppingCartRequestDto requestDto)
    {
        Long userUID = jwtUtil.getUserUID();

        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        Product product = productRepository.findById(requestDto.getProductUID())
                .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

        ShoppingCart shoppingCart = shoppingCartRepository.findProductShoppingCart(target, product);

        if (shoppingCart == null)
        {
            shoppingCartRepository.save(new ShoppingCart(target, product));
            return "추가되었습니다.";
        }
        else
        {
            shoppingCart.delete();
            return "삭제되었습니다.";
        }
    }

    @Transactional
    public ProductShoppingCartListResponseDto productShoppingCartList(ProductShoppingCartListRequestDto requestDto)
    {
        int success = 0;
        int fail = 0;

        Long userUID = jwtUtil.getUserUID();

        User target = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        for (Long productUID : requestDto.getProductUIDList())
        {
            Product product = productRepository.findById(productUID).orElse(null);

            if (product == null)
            {
                fail++;
                continue;
            }

            ShoppingCart shoppingCart = shoppingCartRepository.findProductShoppingCart(target, product);

            if (shoppingCart == null)
            {
                shoppingCartRepository.save(new ShoppingCart(target, product));
                success++;
            }
            else
            {
                fail++;
            }
        }

        return new ProductShoppingCartListResponseDto(success, fail);
    }
}
