select
    distinct ie1_0.id,
    b1_0.id,
    b1_0.createdAt,
    b1_0.deletedAt,
    b1_0.enabled,
    b1_0.name,
    b1_0.updatedAt,
    ie1_0.buyPrice,
    c1_0.item_id,
    c1_1.id,
    c1_1.createdAt,
    c1_1.deletedAt,
    c1_1.enabled,
    c1_1.name,
    c1_1.updatedAt,
    ie1_0.createdAt,
    ie1_0.data,
    ie1_0.deletedAt,
    ie1_0.description,
    ie1_0.enabled,
    i1_0.item_id,
    i1_0.id,
    i1_0.createdAt,
    i1_0.deletedAt,
    i1_0.enabled,
    i1_0.main,
    i1_0.name,
    i1_0.updatedAt,
    ie1_0.name,
    ie1_0.sellPrice,
    ie1_0.stockQuantity,
    ie1_0.updatedAt
from
    items ie1_0
left join item_images i1_0 on
    ie1_0.id = i1_0.item_id
left join brands b1_0 on
    b1_0.id = ie1_0.brand_id
left join item_has_categories c1_0 on
    ie1_0.id = c1_0.item_id
left join categories c1_1 on
    c1_1.id = c1_0.category_id
where
    lower(ie1_0.name) like lower(('%' ||?|| '%')) escape ''
order by
    ie1_0.createdAt
