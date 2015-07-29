package code.elix_x.coremods.invisizones.core;

import net.minecraft.launchwrapper.IClassTransformer;

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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class InvisiZonesTransformer implements IClassTransformer{

	public static Logger logger = LogManager.getLogger("IZ Core");

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		/*if(name.equals(InvisiZonesTranslator.getMapedClassName("client.renderer.WorldRenderer"))){
			logger.info("##################################################");
			logger.info("Patching WorldRenderer");
			byte[] b = patchWorldRenderer(name, bytes);
			logger.info("Patching WorldRenderer Completed");
			logger.info("##################################################");
			return b;
		}
		if(name.equals(InvisiZonesTranslator.getMapedClassName("client.renderer.RenderBlocks"))){
			logger.info("##################################################");
			logger.info("Patching RenderBlocks");
			byte[] b = patchRenderBlocks(name, bytes);
			logger.info("Patching RenderBlocks Completed");
			logger.info("##################################################");
			return b;
		}*/
		if(name.equals(InvisiZonesTranslator.getMapedClassName("world.chunk.Chunk"))){
			if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
				logger.info("##################################################");
				logger.info("Patching Chunk");
				byte[] b = patchChunk(name, bytes);
				logger.info("Patching Chunk Completed");
				logger.info("##################################################");
				return b;
			}
		}
		if(name.equals(InvisiZonesTranslator.getMapedClassName("client.renderer.entity.RenderManager"))){
			if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
				logger.info("##################################################");
				logger.info("Patching RenderManager");
				byte[] b = patchRenderManager(name, bytes);
				logger.info("Patching RenderManager Completed");
				logger.info("##################################################");
				return b;
			}
		}
		if(name.equals(InvisiZonesTranslator.getMapedClassName("client.particle.EffectRenderer"))){
			if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
				logger.info("##################################################");
				logger.info("Patching EffectRenderer");
				byte[] b = patchEffectRenderer(name, bytes);
				logger.info("Patching EffectRenderer Completed");
				logger.info("##################################################");
				return b;
			}
		}
		/*if(name.equals("net.minecraftforge.client.ForgeHooksClient")){
			logger.info("##################################################");
			logger.info("Patching ForgeHooksClient");
			byte[] b = patchForgeHooksClient(name, bytes);
			logger.info("Patching ForgeHooksClient Completed");
			logger.info("##################################################");
			return b;
		}*/
		return bytes;
	}

	private byte[] patchChunk(String name, byte[] bytes) {
		String getBlock = InvisiZonesTranslator.getMapedMethodName("Chunk", "func_150810_a", "getBlock");
		String getBlockDesc = InvisiZonesTranslator.getMapedMethodDesc("Chunk", "func_150810_a", "(III)Lnet/minecraft/block/Block;");

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals(getBlock) && method.desc.equals(getBlockDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getBlock");

					LabelNode skipTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "doGetBlockInsideChunk", "(L" + InvisiZonesTranslator.getMapedClassName("world.chunk.Chunk").replace(".", "/") + ";III)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, skipTo));
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ILOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getBlockInsideChunk", "(L" + InvisiZonesTranslator.getMapedClassName("world.chunk.Chunk").replace(".", "/") + ";III)L" + InvisiZonesTranslator.getMapedClassName("block.Block").replace(".", "/") + ";", false));
					list.add(new InsnNode(Opcodes.ARETURN));
					list.add(skipTo);
					method.instructions.insert(method.instructions.get(0), list);

					logger.info("Patching getBlock Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.info("Patching getBlock Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private byte[] patchEffectRenderer(String name, byte[] bytes) {
		String renderParticles = InvisiZonesTranslator.getMapedMethodName("EffectRenderer", "func_78874_a", "renderParticles");
		String renderParticlesDesc = InvisiZonesTranslator.getMapedMethodDesc("EffectRenderer", "func_78874_a", "(Lnet/minecraft/entity/Entity;F)V");

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals(renderParticles) && method.desc.equals(renderParticlesDesc)){
				try{
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
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "renderParticles", "(L" + InvisiZonesTranslator.getMapedClassName("client.particle.EntityFX").replace(".", "/") + ";)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFEQ, skipTo));
					list.add(new LabelNode());
					method.instructions.insert(targetNode, list);

					method.instructions.insert(targetNode2, skipTo);

					logger.info("Patching renderParticles Completed");
					logger.info("**************************************************");
				}catch(Exception e){
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

	private byte[] patchForgeHooksClient(String name, byte[] bytes) {
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals("onPreRenderWorld") || method.name.equals("onPostRenderWorld")){
				try{
					logger.info("**************************************************");
					logger.info("Patching " + method.name);

					AbstractInsnNode targetNode = null;
					for(AbstractInsnNode currentNode : method.instructions.toArray()){
						if(currentNode.getOpcode() == Opcodes.CHECKCAST){
							targetNode = currentNode;
							break;
						}
					}

					method.instructions.insertBefore(targetNode, new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getOriginalBlockAccess", "(L" + InvisiZonesTranslator.getMapedClassName("world.IBlockAccess").replace(".", "/") + ";)L" + InvisiZonesTranslator.getMapedClassName("world.IBlockAccess").replace(".", "/") + ";", false));

					logger.info("Patching " + method.name + " Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.info("Patching " + method.name + " Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private byte[] patchRenderBlocks(String name, byte[] bytes) {
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals("<init>") && !method.desc.equals("()V")){
				try{
					logger.info("**************************************************");
					logger.info("Patching <init>");

					AbstractInsnNode targetNode = null;
					for(AbstractInsnNode currentNode : method.instructions.toArray()){
						if(currentNode.getOpcode() == Opcodes.ALOAD){
							VarInsnNode var = (VarInsnNode) currentNode;
							if(var.var == 1){
								targetNode = currentNode;
								break;
							}
						}
					}

					method.instructions.insert(targetNode, new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getBlockAccess", "(L" + InvisiZonesTranslator.getMapedClassName("world.IBlockAccess").replace(".", "/") + ";)L" + InvisiZonesTranslator.getMapedClassName("world.IBlockAccess").replace(".", "/") + ";", false));

					logger.info("Patching <init> Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.info("Patching <init> Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private byte[] patchBlock(String name, byte[] bytes) {
		String getLightOpacity = "getLightOpacity";
		String getLightOpacityDesc = "(L" + InvisiZonesTranslator.getMapedClassName("world.IBlockAccess").replace(".", "/") + ";III)I";

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals(getLightOpacity) && method.desc.equals(getLightOpacityDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching getLightOpacity");

					InsnList list = new InsnList();
					list.add(new LabelNode());
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ALOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 2));
					list.add(new VarInsnNode(Opcodes.ILOAD, 3));
					list.add(new VarInsnNode(Opcodes.ILOAD, 4));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "getLightOpacity", "(L" + InvisiZonesTranslator.getMapedClassName("block.Block").replace(".", "/") + ";L" + InvisiZonesTranslator.getMapedClassName("world.IBlockAccess").replace(".", "/") + ";III)I", false));
					list.add(new InsnNode(Opcodes.IRETURN));
					list.add(new LabelNode());
					method.instructions.insert(list);

					logger.info("Patching getLightOpacity Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.info("Patching getLightOpacity Failed With Exception:");
					e.printStackTrace();
					logger.info("**************************************************");
				}
			}
		}

		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		return writer.toByteArray();
	}

	private byte[] patchRenderManager(String name, byte[] bytes) {
		String func_147939_a = InvisiZonesTranslator.getMapedMethodName("RenderManager", "func_147939_a", "func_147939_a");
		String func_147939_aDesc = InvisiZonesTranslator.getMapedMethodDesc("RenderManager", "func_147939_a", "(Lnet/minecraft/entity/Entity;DDDFFZ)Z");

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals(func_147939_a) && method.desc.equals(func_147939_aDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching func_147939_a");

					LabelNode goTo = new LabelNode();

					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 1));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "renderEntity", "(L" + InvisiZonesTranslator.getMapedClassName("entity.Entity").replace(".", "/") + ";)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFNE, goTo));
					list.add(new InsnNode(Opcodes.ICONST_0));
					list.add(new InsnNode(Opcodes.IRETURN));
					list.add(goTo);
					method.instructions.insert(list);

					logger.info("Patching func_147939_a Completed");
					logger.info("**************************************************");
				}catch(Exception e){
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

	private byte[] patchWorldRenderer(String name, byte[] bytes) {
		String updateRenderer = InvisiZonesTranslator.getMapedMethodName("WorldRenderer", "func_147892_a", "updateRenderer");
		String updateRendererDesc = InvisiZonesTranslator.getMapedMethodDesc("WorldRenderer", "func_147892_a", "(Lnet/minecraft/entity/EntityLivingBase;)V");

		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);

		for(MethodNode method : classNode.methods){
			if(method.name.equals(updateRenderer) && method.desc.equals(updateRendererDesc)){
				try{
					logger.info("**************************************************");
					logger.info("Patching updateRenderer");

					/*
					 * INVOKEVIRTUAL net/minecraft/world/ChunkCache.getBlock (III)Lnet/minecraft/block/Block;
					 * ASTORE 24
					 */

					AbstractInsnNode targetNode = null;
					AbstractInsnNode targetNode2 = null;

					for(AbstractInsnNode currentNode : method.instructions.toArray()){
						if(currentNode.getOpcode() == Opcodes.INVOKEVIRTUAL){
							MethodInsnNode m = (MethodInsnNode) currentNode;
							if(m.owner.replace("/", ".").equals(InvisiZonesTranslator.getMapedClassName("world.ChunkCache")) && m.name.equals(InvisiZonesTranslator.getMapedMethodName("ChunkCache", "func_147439_a", "getBlock"))){
								/*if(m.getNext().getOpcode() == Opcodes.ASTORE){
									VarInsnNode var = (VarInsnNode) m.getNext();
									if(var.var == 24){*/
								targetNode = currentNode.getNext();
								break;
								/*}
								}*/
							}
						}
					}

					/*
					 * ICONST_0
					 * INVOKEVIRTUAL net/minecraft/client/renderer/RenderBlocks.setRenderAllFaces (Z)V
					 */

					/*	for(AbstractInsnNode currentNode : method.instructions.toArray()){
						if(currentNode.getOpcode() == Opcodes.INVOKEVIRTUAL){
							MethodInsnNode m = (MethodInsnNode) currentNode;
							if(m.owner.replace("/", ".").equals(InvisiZonesTranslator.getMapedClassName("client.renderer.RenderBlocks")) && m.name.equals(InvisiZonesTranslator.getMapedMethodName("RenderBlocks", "func_147753_b", "setRenderAllFaces"))){
								if(m.getPrevious().getOpcode() == Opcodes.ICONST_0){
									targetNode2 = currentNode;
									break;
								}
							}
						}
					}*/

					boolean b = false;

					for(AbstractInsnNode currentNode : method.instructions.toArray()){
						if(currentNode.getOpcode() == Opcodes.IINC){
							if(!b){
								b = true;
							} else {	
								targetNode2 = currentNode.getPrevious().getPrevious().getPrevious();
								break;
							}
						}
					}

					LabelNode goToEnd = new LabelNode();
					//					LabelNode goToBeg = new LabelNode();

					/*
					 * ALOAD 1
					 * ILOAD 23
					 * ILOAD 21
					 * ILOAD 22
					 * INVOKESTATIC code.elix_x.coremods.invisizones.core.InviziZoneHooks.renderBlock(Lnet.minecraft.entity.EntityLivingBase;III)Z
					 * IFEQ L48
					 */

					//					Opcodes.IFEQ
					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 0));
					list.add(new VarInsnNode(Opcodes.ALOAD, 16));
					list.add(new VarInsnNode(Opcodes.ALOAD, 1));
					list.add(new VarInsnNode(Opcodes.ILOAD, 23));
					list.add(new VarInsnNode(Opcodes.ILOAD, 21));
					list.add(new VarInsnNode(Opcodes.ILOAD, 22));
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "renderBlock", "(L" + InvisiZonesTranslator.getMapedClassName("client.renderer.WorldRenderer").replace(".", "/") + ";L" + InvisiZonesTranslator.getMapedClassName("client.renderer.RenderBlocks").replace(".", "/") + ";L" + InvisiZonesTranslator.getMapedClassName("entity.EntityLivingBase").replace(".", "/") + ";III)Z", false));
					list.add(new JumpInsnNode(Opcodes.IFEQ, goToEnd));
					method.instructions.insert(targetNode, list);

					InsnList l = new InsnList();
					l.add(goToEnd);
					l.add(new VarInsnNode(Opcodes.ALOAD, 0));
					l.add(new VarInsnNode(Opcodes.ALOAD, 16));
					l.add(new VarInsnNode(Opcodes.ALOAD, 1));
					l.add(new VarInsnNode(Opcodes.ILOAD, 23));
					l.add(new VarInsnNode(Opcodes.ILOAD, 21));
					l.add(new VarInsnNode(Opcodes.ILOAD, 22));
					l.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "code.elix_x.coremods.invisizones.core.InvisiZoneHooks".replace(".", "/"), "postRenderBlock", "(L" + InvisiZonesTranslator.getMapedClassName("client.renderer.WorldRenderer").replace(".", "/") + ";L" + InvisiZonesTranslator.getMapedClassName("client.renderer.RenderBlocks").replace(".", "/") + ";L" + InvisiZonesTranslator.getMapedClassName("entity.EntityLivingBase").replace(".", "/") + ";III)V", false));
					method.instructions.insert(targetNode2, l);

					logger.info("Patching updateRenderer Completed");
					logger.info("**************************************************");
				}catch(Exception e){
					logger.info("Patching updateRenderer Failed With Exception:");
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
