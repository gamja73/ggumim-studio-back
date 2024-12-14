package pbl.project.ggumimstudioBack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.user.dto.request.ProductShoppingCartListRequestDto;
import pbl.project.ggumimstudioBack.user.dto.request.ProductShoppingCartRequestDto;
import pbl.project.ggumimstudioBack.user.dto.request.ProductWishRequestDto;
import pbl.project.ggumimstudioBack.user.dto.response.ProductShoppingCartListResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageResponseData;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageShoppingCartResponseDto;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageWishListResponseDto;
import pbl.project.ggumimstudioBack.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController
{
    private final UserService userService;

    @GetMapping("/myPage")
    public CommonApiResponse<UserMyPageResponseData> myPage()
    {
        return CommonApiResponse.OK(userService.myPage());
    }

    @GetMapping("/myPage/wish")
    public CommonApiResponse<PaginationResponse<UserMyPageWishListResponseDto>> myPageWishList(BaseSearchParamRequestDto requestDto)
    {
        return CommonApiResponse.OK(userService.myPageWishList(requestDto));
    }

    @PostMapping("/wish")
    public CommonApiResponse<String> productWish(@RequestBody ProductWishRequestDto requestDto)
    {
        return CommonApiResponse.OK(userService.productWish(requestDto));
    }

    @GetMapping("/myPage/shoppingCart")
    public CommonApiResponse<List<UserMyPageShoppingCartResponseDto>> myPageWishList()
    {
        return CommonApiResponse.OK(userService.myPageShoppingCart());
    }

    @PostMapping("/shoppingCart")
    public CommonApiResponse<String> productShoppingCart(@RequestBody ProductShoppingCartRequestDto requestDto)
    {
        return CommonApiResponse.OK(userService.productShoppingCart(requestDto));
    }

    @PostMapping("/shoppingCart/list")
    public CommonApiResponse<ProductShoppingCartListResponseDto> productShoppingCartList(@RequestBody ProductShoppingCartListRequestDto requestDto)
    {
        return CommonApiResponse.OK(userService.productShoppingCartList(requestDto));
    }
}
