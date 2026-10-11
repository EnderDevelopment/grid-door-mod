package com.example.griddoormod.block;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Material;

public class GridDoorBlock extends DoorBlock {
    public GridDoorBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD).strength(3.0F).noOcclusion(), BlockSetType.OAK);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }

    @Override
    public boolean canConnect(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        Block block = world.getBlockState(pos.relative(side)).getBlock();
        return block instanceof FenceBlock || block instanceof FenceGateBlock;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.getItem() == Items.PAPER && heldItem.hasTag() && "worker_card".equals(heldItem.getTag().getString("type"))) {
            state = state.cycle(DoorBlock.OPEN);
            world.setBlock(pos, state, 10);
            world.levelEvent(player, state.getValue(DoorBlock.OPEN) ? 1005 : 1011, pos, 0);
            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            player.displayClientMessage(new TranslationTextComponent("message.griddoormod.need_worker_card"), true);
            return ActionResultType.PASS;
        }
    }
}
