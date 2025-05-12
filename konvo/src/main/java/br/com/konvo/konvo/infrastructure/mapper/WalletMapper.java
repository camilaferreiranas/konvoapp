package br.com.konvo.konvo.infrastructure.mapper;

import br.com.konvo.konvo.domain.model.Stock;
import br.com.konvo.konvo.domain.model.UserClient;
import br.com.konvo.konvo.domain.model.Wallet;
import br.com.konvo.konvo.infrastructure.persistence.StockEntity;
import br.com.konvo.konvo.infrastructure.persistence.UserClientEntity;
import br.com.konvo.konvo.infrastructure.persistence.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Mapping(source = "userClient", target = "userClient", ignore = true)
    Wallet toDomain(WalletEntity walletEntity);
    WalletEntity toEntity(Wallet domain);

    Stock toDomain(StockEntity entity);
    StockEntity toEntity(Stock domain);
    List<StockEntity> toEntityList(List<Stock> domains);

    UserClient toDomain(UserClientEntity entity);
    UserClientEntity toEntity(UserClient domain);


}
