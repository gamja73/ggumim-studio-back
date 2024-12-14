package pbl.project.ggumimstudioBack.user.repository;

import pbl.project.ggumimstudioBack.common.dto.request.BaseSearchParamRequestDto;
import pbl.project.ggumimstudioBack.common.dto.response.PaginationResponse;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.user.dto.response.UserMyPageWishListResponseDto;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.entity.WishList;

public interface WishListRepositoryCustom
{
    WishList findProductWish(User target, Product wishProduct);

    PaginationResponse<UserMyPageWishListResponseDto> myPageWishList(User user, BaseSearchParamRequestDto requestDto);
}
