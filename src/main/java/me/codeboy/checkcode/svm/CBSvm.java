package me.codeboy.checkcode.svm;

import java.io.IOException;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;

/**
 * SVM分类器，实现预测与精度计算
 * 
 * @author Yuedong Li
 * 
 */
public class CBSvm {
	private double accuracy;
	private final String modelPath;
	private svm_model model ;

	public CBSvm(String modelPath) throws IOException {
		this.modelPath = modelPath;
		model = svm.svm_load_model(modelPath);
	}

	public CBSvm(String trainingSetPath, String modelPath) {
		this.modelPath = modelPath;
		try {
			CBSvmTrain.main(new String[] { trainingSetPath, modelPath });
			model = svm.svm_load_model(modelPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过训练，对测试数据进行测试，得到精度
	 * 
	 * @param testSetPath
	 *            测试集
	 * @param resultPath
	 *            测试集结果保存路径
	 * @return 模型精度
	 * @throws IOException
	 */
	public double getAccuracy(String testSetPath, String resultPath)
			throws IOException {
		accuracy = CBSvmPredict.main(new String[] { testSetPath, modelPath,
				resultPath });
		return accuracy;
	}

	/**
	 * 通过模型得到预测结果
	 * 
	 * @param values
	 *            对应属性值
	 * @return 预测分类
	 */
	public double getClassification(double[] values) {
		int len = values.length;
		svm_node[] node = new svm_node[len];
		for (int i = 0; i < len; i++) {
			node[i] = new svm_node();
			node[i].index = i + 1;
			node[i].value = values[i];
		}
		return svm.svm_predict(model, node);
	}
}
