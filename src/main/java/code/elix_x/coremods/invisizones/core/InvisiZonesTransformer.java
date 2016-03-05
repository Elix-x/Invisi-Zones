package code.elix_x.coremods.invisizones.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class InvisiZonesTransformer implements IClassTransformer {

	public static final Logger logger = LogManager.getLogger("IZ Core");

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes){
		if(name.equals("net.minecraft.world.chunk.Chunk")){
			logger.info("##################################################");
			logger.info("Patching Chunk");
			byte[] b = patchChunk(name, bytes);
			logger.info("Patching Chunk Completed");
			logger.info("##################################################");
			return b;
		}
		if(name.equals("net.minecraft.client.renderer.entity.RenderManager")){
			logger.info("##################################################");
			logger.info("Patching RenderManager");
			byte[] b = patchRenderManager(name, bytes);
			logger.info("Patching RenderManager Completed");
			logger.info("##################################################");
			return b;
		}
		if(name.equals("net.minecraft.client.particle.EffectRenderer")){
			logger.info("##################################################");
			logger.info("Patching EffectRenderer");
			byte[] b = patchEffectRenderer(name, bytes);
			logger.info("Patching EffectRenderer Completed");
			logger.info("##################################################");
			return b;
		}
		return bytes;
	}

	private byte[] patchChunk(String name, byte[] bytes){
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if((method.name.equals("getBlock") || method.name.equals("func_150810_a")) && method.desc.equals("(III)Lnet/minecraft/block/Block;")){
				try {
					logger.info("**************************************************");
					logger.info("Patching getBlock");

					LabelNode skipTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "doGetBlockInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, skipTo));
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getBlockInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)L" + "net.minecraft.block.Block".replace(".", "/") + ";", false));
					list.add(new InsnNode(Opcodes.ARETURN));
					list.add(skipTo);
					method.instructions.insert(method.instructions.get(0), list);

					logger.info("Patching getBlock Completed");
					logger.info("**************************************************");
				} catch(Exception e){
					logger.info("Patching getBlock Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
			if((method.name.equals("getBlockMetadata") || method.name.equals("func_76628_c")) && method.desc.equals("(III)I")){
				try {
					logger.info("**************************************************");
					logger.info("Patching getBlockMetadata");

					/*AbstractInsnNode target = null;
					for(AbstractInsnNode node : method.instructions.toArray()){
						if(node.getOpcode() == Opcodes.F_SAME){
							target = node;
							break;
						}
					}*/

					LabelNode skipTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new LabelNode());
					//					list.add(new InsnNode(Opcodes.F_SAME));
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "doGetBlockMetadataInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, skipTo));
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getBlockMetadataInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)I", false));
					list.add(new InsnNode(Opcodes.IRETURN));
					list.add(skipTo);
					//					list.add(new InsnNode(Opcodes.F_SAME));
					method.instructions.insert(/*target,*/ list);

					logger.info("Patching getBlockMetadata Completed");
					logger.info("**************************************************");
				} catch(Exception e){
					logger.info("Patching getBlockMetadata Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
			if(method.name.equals("func_150806_e") && method.desc.equals("(III)Lnet/minecraft/tileentity/TileEntity;")){
				try {
					logger.info("**************************************************");
					logger.info("Patching func_150806_e");

					LabelNode skipTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "doGetTileEntityInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, skipTo));
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getTileEntityInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)L" + "net.minecraft.tileentity.TileEntity".replace(".", "/") + ";", false));
					list.add(new InsnNode(Opcodes.ARETURN));
					list.add(skipTo);
					method.instructions.insert(list);

					logger.info("Patching func_150806_e Completed");
					logger.info("**************************************************");
				} catch(Exception e){
					logger.info("Patching func_150806_e Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
			if(method.name.equals("getTileEntityUnsafe")){
				try {
					logger.info("**************************************************");
					logger.info("Patching getTileEntityUnsafe");

					LabelNode skipTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "doGetTileEntityInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, skipTo));
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getTileEntityInsideChunk", "(L" + "net.minecraft.world.chunk.Chunk".replace(".", "/") + ";III)L" + "net.minecraft.tileentity.TileEntity".replace(".", "/") + ";", false));
					list.add(new InsnNode(Opcodes.ARETURN));
					list.add(skipTo);
					method.instructions.insert(list);

					logger.info("Patching getTileEntityUnsafe Completed");
					logger.info("**************************************************");
				} catch(Exception e){
					logger.info("Patching getTileEntityUnsafe Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private byte[] patchEffectRenderer(String name, byte[] bytes){
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if((method.name.equals("renderParticles") || method.name.equals("func_78874_a")) && method.desc.equals("(Lnet/minecraft/entity/Entity;F)V")){
				try {
					logger.info("**************************************************");
					logger.info("Patching renderParticles");

					AbstractInsnNode targetNode = null;
					for(AbstractInsnNode currentNode : method.instructions.toArray()){
						if(currentNode.getOpcode() == Opcodes.ALOAD){
							if(((VarInsnNode) currentNode).var == 12){
								if(currentNode.getNext().getOpcode() == Opcodes.ALOAD){
									if(((VarInsnNode) currentNode.getNext()).var == 10){
										targetNode = currentNode.getPrevious();
										break;
									}
								}
							}
						}
					}

					AbstractInsnNode targetNode2 = targetNode;
					while((targetNode2 = targetNode2.getNext()) != null){
						if(targetNode2.getOpcode() == Opcodes.INVOKEVIRTUAL){
							break;
						}
					}

					LabelNode skipTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 12));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "renderParticles", "(L" + "net.minecraft.client.particle.EntityFX".replace(".", "/") + ";)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFEQ, skipTo));
					list.add(new LabelNode());
					method.instructions.insert(targetNode, list);

					method.instructions.insert(targetNode2, skipTo);

					logger.info("Patching renderParticles Completed");
					logger.info("**************************************************");
				} catch(Exception e){
					logger.info("Patching renderParticles Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private byte[] patchRenderManager(String name, byte[] bytes){
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals("func_147939_a") && method.desc.equals("(Lnet/minecraft/entity/Entity;DDDFFZ)Z")){
				try {
					logger.info("**************************************************");
					logger.info("Patching func_147939_a");

					LabelNode goTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 1));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "renderEntity", "(L" + "net.minecraft.entity.Entity".replace(".", "/") + ";)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, goTo));
					list.add(new InsnNode(Opcodes.ICONST_0));
					list.add(new InsnNode(Opcodes.IRETURN));
					list.add(goTo);
					method.instructions.insert(list);

					logger.info("Patching func_147939_a Completed");
					logger.info("**************************************************");
				} catch(Exception e){
					logger.info("Patching func_147939_a Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

}
