package dev.xkmc.golemdungeons.content.client;

import dev.xkmc.golemdungeons.init.GolemDungeons;
import dev.xkmc.modulargolems.content.client.armor.GolemModelPath;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import java.util.List;

import static dev.xkmc.golemdungeons.content.client.GDModelPaths.*;
import static dev.xkmc.modulargolems.content.client.armor.GolemEquipmentModels.buildGolemBaseLayers;

public class EvilArmors {

	public static final ModelLayerLocation HELMET_LAYER = new ModelLayerLocation(GolemDungeons.loc("samurai_helmet"), "main");
	public static final ModelLayerLocation CHESTPLATE_LAYER = new ModelLayerLocation(GolemDungeons.loc("samurai_chestplate"), "main");
	public static final ModelLayerLocation SHINGUARD_LAYER = new ModelLayerLocation(GolemDungeons.loc("samurai_shinguard"), "main");

	static {
		GolemModelPath.register(SAMURAI_HELMET,
				new GolemModelPath(HELMET_LAYER, List.of(
						List.of("head", "head1"),
						List.of("head", "head1", "cube_r3"),
						List.of("head", "head1", "cube_r4"),
						List.of("head", "head1", "cube_r5")
				)));

		GolemModelPath.register(SAMURAI_CHESTPLATE,
				new GolemModelPath(CHESTPLATE_LAYER, List.of(
						List.of("body", "body1"),
						List.of("body", "body1", "cube_r1"),
						List.of("body", "body1", "cube_r2"),
						List.of("right_arm", "body2"),
						List.of("right_arm", "body2", "cube_r6"),
						List.of("left_arm", "body3"),
						List.of("left_arm", "body3", "cube_r7")
				)));

		GolemModelPath.register(SAMURAI_LEGGING,
				new GolemModelPath(SHINGUARD_LAYER, List.of(
						List.of("body", "legs1"),
						List.of("right_leg", "legs2"),
						List.of("left_leg", "legs3")
				)));
	}

	public static LayerDefinition createHelmet() {
		MeshDefinition mesh = buildGolemBaseLayers();
		PartDefinition root = mesh.getRoot();

		PartDefinition head = root.getChild("head");

		PartDefinition head1 = head.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(28, 25).addBox(-5.5F, -2.5F, -4.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -10.0F, -2.0F));

		PartDefinition cube_r3 = head1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(44, 72).addBox(-2.2F, -4.0F, -1.1F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 7.0F, -3.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r4 = head1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 72).addBox(0.0F, -2.0F, -4.1F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r5 = head1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(62, 12).addBox(-9.0F, -4.0F, -0.9F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -4.0F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(mesh, 128, 128);
	}

	public static LayerDefinition createChestplate() {
		MeshDefinition mesh = buildGolemBaseLayers();
		PartDefinition root = mesh.getRoot();


		PartDefinition body = root.getChild("body");

		PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-18.5F, -14.0F, -1.5F, 19.0F, 13.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 11.0F, -5.0F));

		PartDefinition cube_r1 = body1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 25).addBox(0.5F, -4.0F, -6.0F, 1.0F, 21.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -30.0F, 17.0F, -0.0857F, 0.0161F, -0.0027F));

		PartDefinition cube_r2 = body1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(66, 65).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 25.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -29.0F, 11.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition right_arm = root.getChild("right_arm");

		PartDefinition body2 = right_arm.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 59).addBox(-13.5F, -15.0F, -3.5F, 5.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 74).addBox(-18.0F, -21.0F, 0.0F, 5.0F, 17.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 31.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 44).addBox(-5.5F, -3.0F, -4.0F, 11.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.0F, -29.0F, 1.0F, 0.0F, -0.0436F, -1.2217F));

		PartDefinition left_arm = root.getChild("left_arm");

		PartDefinition body3 = left_arm.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(64, 24).addBox(8.5F, -15.0F, -3.5F, 5.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(10, 74).addBox(13.0F, -21.0F, -1.0F, 5.0F, 17.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 31.0F, 0.0F));

		PartDefinition cube_r7 = body3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(28, 58).addBox(-10.0F, -6.0F, -1.0F, 11.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -24.0F, -3.0F, 0.0F, 0.0F, 1.2217F));


		return LayerDefinition.create(mesh, 128, 128);
	}

	public static LayerDefinition createLeggings() {
		MeshDefinition mesh = buildGolemBaseLayers();
		PartDefinition root = mesh.getRoot();

		PartDefinition body = root.getChild("body");

		PartDefinition legs1 = body.addOrReplaceChild("legs1", CubeListBuilder.create().texOffs(62, 0).addBox(-9.5F, -5.0F, -1.0F, 11.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 16.0F, -3.0F));

		PartDefinition right_leg = root.getChild("right_leg");

		PartDefinition legs2 = right_leg.addOrReplaceChild("legs2", CubeListBuilder.create().texOffs(66, 39).addBox(-8.0F, -12.0F, -3.5F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 13.0F, 0.0F));

		PartDefinition left_leg = root.getChild("left_leg");

		PartDefinition legs3 = left_leg.addOrReplaceChild("legs3", CubeListBuilder.create().texOffs(66, 52).addBox(1.0F, -12.0F, -3.5F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 13.0F, 0.0F));

		return LayerDefinition.create(mesh, 128, 128);
	}

}
